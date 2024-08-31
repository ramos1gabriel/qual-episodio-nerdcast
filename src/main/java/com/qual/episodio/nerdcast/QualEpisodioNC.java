package com.qual.episodio.nerdcast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.qual.episodio.nerdcast.csv.LerCSV;
import com.qual.episodio.nerdcast.model.Fala;
import com.qual.episodio.nerdcast.model.Nerdcast;

public class QualEpisodioNC {

	public static void main(String[] args) {
		QualEpisodioNC qenc = new QualEpisodioNC();
		qenc.lerJSON(); //vai ate o epi 921
	}
	
	public void lerJSON() {
		String csvFile = LerCSV.class.getClassLoader().getResource("json-nc.txt").getPath();
		
		ArrayList<Nerdcast> episodios = new ArrayList<Nerdcast>();
		
		System.out.println("Lendo dataset dos nerdcasts 01 a 921...");
		
		int cont = 1;
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), "UTF-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
            	
            	//valida json antes de converter
            	if(isValidJson(line, Nerdcast.class, cont)) {
            		//converter string para json
                	Gson gson = new Gson();
                    Nerdcast nc = gson.fromJson(line, Nerdcast.class);
                    episodios.add(nc);
            	}
            	cont++;
            }
		} catch (Exception e) {
			System.out.println("Erro na linha: "+cont);
            e.printStackTrace();
        }
		
		String search = "centro espírita";
		
		System.out.println("Leitura finalizada... Total de linhas: "+ cont);
		System.out.println("Iniciando busca por: "+search);
		
		Pattern pattern = Pattern.compile(search, Pattern.CASE_INSENSITIVE);
		
		String episodio = "";
		String minutagem = "";
		
		//pesquisa palavra-chave
		for (Nerdcast nerdcast : episodios) {
			episodio = nerdcast.getTituloFormatado();
			for (Fala fala : nerdcast.getFalas()) {
				Matcher matcher = pattern.matcher(fala.getText());
				if (matcher.find()) {
					minutagem = fala.getMinutagem();
					System.out.println("Encontrado em: "+episodio+" | "+minutagem);
				}
			}
		}
	}
	
	public static boolean isValidJson(String jsonString, Class<?> classe, int cont) {
        Gson gson = new Gson();
        try {
            gson.fromJson(jsonString, classe);
            return true;
        } catch (JsonSyntaxException e) {
        	System.out.println("JSON da linha "+cont+" é invalido!");
            return false;
        }
    }
}
