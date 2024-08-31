package com.qual.episodio.nerdcast.csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/*
 * https://www.kaggle.com/datasets/leonardocosta1206/nerdcast-transcriptions
 */
public class GerarJsonNC {
	
	public static void main(String[] args) {
		GerarJsonNC gjnc = new GerarJsonNC();
		gjnc.gerarJSONTXT();
	}
	
	public void gerarJSONTXT() {
		String csvFile = GerarJsonNC.class.getClassLoader().getResource("nc-dataset.csv").getPath();
		StringBuffer dados = new StringBuffer();
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), StandardCharsets.UTF_8))) {
            String line;
            String csvSeparator = ",";
            boolean isHeader = true; //pular cabecalho
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                
                //pega numero do NC
                String[] values = line.split(csvSeparator);
                
                int start = line.indexOf(",\"[");
                int end = line.indexOf("]\",,");
                
                if(end < 1) {
                	end = line.indexOf("]\",");
                }
                
                if(start >= 1 && end >= 1) {
                	String sub = line.substring(start, end);
                    String subTratado = sub.replace("\"\"", "\"").replace(",\"[", "[");
                    String subTimestamp = subTratado.replace("[", "\"[").replace("]", "]\"").replace("\"[{", "[{").replace("}]\"", "}]");
                    
                    //dados.append(subTratado+"\n");
                    String titulo = "{\"titulo\":\""+values[1].replace("\"", "")+"\",\"falas\":[{\"timestamp\":";
                    String subComTitulo = subTimestamp.replace("[{\"timestamp\":", titulo);
                    dados.append(subComTitulo+"]}\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //criar e escrever no arquivo
        String filename = "C:\\NC\\json-nc.txt";
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), StandardCharsets.UTF_8))) {
            writer.write(dados.toString());
            System.out.println("Arquivo "+filename+" foi gerado com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
