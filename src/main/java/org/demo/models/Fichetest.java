package org.demo.models;

import java.util.List;

public class Fichetest {
private FichePFE f;
private EncadrantSociete es;
private Societe soc;
private List<Technologies> tech;
private List<Fonctionalite>  foncts;
private List<Problematique>  probs;






















public Fichetest() {
	super();
}







public Fichetest(FichePFE f, EncadrantSociete es, Societe soc, List<Technologies> tech, List<Fonctionalite> foncts,
		List<Problematique> probs) {
	super();
	this.f = f;
	this.es = es;
	this.soc = soc;
	this.tech = tech;
	this.foncts = foncts;
	this.probs = probs;
}







public Societe getSoc() {
	return soc;
}







public void setSoc(Societe soc) {
	this.soc = soc;
}







public FichePFE getF() {
	return f;
}







public void setF(FichePFE f) {
	this.f = f;
}







public List<Fonctionalite> getFoncts() {
	return foncts;
}







public void setFoncts(List<Fonctionalite> foncts) {
	this.foncts = foncts;
}







public List<Problematique> getProbs() {
	return probs;
}







public void setProbs(List<Problematique> probs) {
	this.probs = probs;
}







public EncadrantSociete getEs() {
	return es;
}







public void setEs(EncadrantSociete es) {
	this.es = es;
}







public List<Technologies> getTech() {
	return tech;
}







public void setTech(List<Technologies> tech) {
	this.tech = tech;
}





}
