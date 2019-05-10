package converter.service;

public class HelpService extends GlobalService{
	public void executerUC(String[] parameters) {
		System.out.println("NAME");
		System.out.println("converter - xml, json, yaml file converter");
		System.out.println("SYNOPSIS");
		System.out.println("converter [Options] [Source] [Target]");
		System.out.println("Options");
		System.out.println("--help");
		System.out.println("Display this help");
		System.out.println("-o format");
		System.out.println("output format for the destination file. Formats are xml, json, yaml");
	}
}
