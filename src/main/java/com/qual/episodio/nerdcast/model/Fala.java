package com.qual.episodio.nerdcast.model;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Fala extends Nerdcast implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String timestamp;
	private String text;
	
	public String getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	//tratar timestamp do dataset "[0.0, 7.0]"
	public String getTimestampFormatado() {
		String formatado = null;
		if(getTimestamp() != null && !getTimestamp().isEmpty()) {
			String ttTratado = getTimestamp().replace("[", "").replace("]", "");
			String[] ttSeparado = ttTratado.split(",");
			if(ttSeparado.length >= 0) {
				String[] ttSemPonto = ttSeparado[0].split("\\.");
				if(ttSemPonto.length >= 0) {
					formatado = ttSemPonto[0];
				}
			}
		}
		
		return formatado;
	}
	
	
	//converter timestamp para minutagem
	public String getMinutagem() {
		String minutagem = null;
		if(getTimestampFormatado() != null && !getTimestampFormatado().isEmpty()) {
			ZoneId gmt = ZoneId.of("GMT");
			Instant instant = Instant.ofEpochSecond(Long.valueOf(getTimestampFormatado()));
	        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, gmt);
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	        minutagem = dateTime.format(formatter);
		}
		
		return minutagem;
    }
}
