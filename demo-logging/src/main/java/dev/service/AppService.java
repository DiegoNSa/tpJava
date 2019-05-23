package dev.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.console.App;

public class AppService {
	private static final Logger LOG = LoggerFactory.getLogger(AppService.class);
	
	public void executer(String param) {
		LOG.debug("Traitement 1 : param = {}",param);
	}

}
