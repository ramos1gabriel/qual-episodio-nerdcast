package com.qual.episodio.nerdcast.csv;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LerCSV {
	
	public static void main(String[] args) {
		LerCSV lcsv = new LerCSV();
		lcsv.lerCsv();
	}
	
	public void lerCsv() {
		String csvFile = LerCSV.class.getClassLoader().getResource("nc-dataset.csv").getPath();
		
        String search = "A calça não";
        System.out.println("Palavra-chave: "+search);
        
        Pattern pattern = Pattern.compile(search, Pattern.CASE_INSENSITIVE);
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), "UTF-8"))) {
            String line;
            String csvSeparator = ",";
            boolean isHeader = true; //pular cabecalho
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                String[] values = line.split(csvSeparator);
                //id, title, summary, url, transcription_text, transcription_chunks, image, duration, link, transcription_time
                
                //com find
				Matcher matcher = pattern.matcher(line);
				if (matcher.find()) {
					System.out.println("Encontrado em: " + values[1]);
				}
				
				//com contains
				if (line.contains(search)) {
					System.out.println(values[1]);
					//procurar no split
					for (String valor : values) {
						Matcher matcher2 = pattern.matcher(valor);
						if (matcher2.find()) {
							System.out.println(valor);
						}
					}
				}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
