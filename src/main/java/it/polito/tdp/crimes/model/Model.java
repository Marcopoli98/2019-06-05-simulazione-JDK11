package it.polito.tdp.crimes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.crimes.db.EventsDao;

public class Model {

	private Graph<Integer, DefaultWeightedEdge> grafo;
	private EventsDao dao;

	public Model() {
		this.dao = new EventsDao();
	}

	public void creaGrafo(int anno) {

		this.grafo = new SimpleWeightedGraph(DefaultWeightedEdge.class);
		Map<Integer, Info> mappa = this.dao.getArchi(anno);

		// crea vertici
		for (Integer i : this.dao.getVertici()) {
			this.grafo.addVertex(i);
		}

		// crea archi
		for (Integer i1 : this.grafo.vertexSet()) {
			for (Integer i2 : this.grafo.vertexSet()) {
				if (!i1.equals(i2) && !grafo.containsEdge(grafo.getEdge(i1, i2))) {
					Graphs.addEdgeWithVertices(grafo, i1, i2, LatLngTool.distance(mappa.get(i1).getPunto(),
							mappa.get(i2).getPunto(), LengthUnit.KILOMETER));
				}
			}
		}
	}
	
	public List<Adiacenza> getVerticiAdiacenti(int vertice){
		
		List<Adiacenza> lista = new ArrayList<>();
		
		for(Integer i : Graphs.neighborListOf(grafo, vertice)) {
			lista.add(new Adiacenza(i, grafo.getEdgeWeight(grafo.getEdge(vertice, i))));
		}
		
		Collections.sort(lista);
		
		return lista;
		
	}
	
	public Graph<Integer, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}
	
	
	public int numVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int numArchi() {
		return this.grafo.edgeSet().size();
	}
	
	

}
