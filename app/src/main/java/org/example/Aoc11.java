package org.example;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import com.google.common.collect.Maps;
import com.google.common.graph.Graph;
import com.google.common.graph.MutableNetwork;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.Network;
import com.google.common.graph.NetworkBuilder;
import com.google.common.graph.ValueGraphBuilder;

public class Aoc11 {

    @SuppressWarnings("UnstableApiUsage")
    public static void main(final String[] args) {
        final InputStream is = App.class.getClassLoader().getResourceAsStream("input.txt");
        final BufferedReader br = new BufferedReader(new InputStreamReader(is));
        final String[] lines = br.lines().toArray(String[]::new);
        final MutableNetwork<MyNode, MyEdge> network = NetworkBuilder.directed().allowsSelfLoops(false).allowsParallelEdges(false).build();
        final Map<String, MyNode> nodeMap = Maps.newHashMap();
        final MyNode outNode = new MyNode("out", 1);
        network.addNode(outNode);
        nodeMap.put("out", outNode);
        for (int i = 0; i < lines.length; i++) {
            final String[] line = lines[i].split(" ");
            final String nodeString = line[0].substring(0, 3);
            final MyNode predecessorNode = nodeMap.computeIfAbsent(nodeString, s -> new MyNode(s, 0));
            network.addNode(predecessorNode);
            for (int j = 1; j < line.length; j++) {
                final MyNode successorNode = nodeMap.computeIfAbsent(line[j], s -> new MyNode(s, 0));
                network.addEdge(predecessorNode, successorNode, new MyEdge(nodeString, line[j], 0));
            }
        }
        /*
        MutableValueGraph<String, Integer> vgraph = ValueGraphBuilder.directed().allowsSelfLoops(false).directed().build();
        for (int i = 0; i < lines.length; i++) {
            String[] line = lines[i].split(" ");
            String node = line[0].substring(0, 3);
            vgraph.addNode(node);
            for (int j = 1; j < line.length; j++) {
                vgraph.putEdgeValue(node, line[j], 0);
            }
        }
        */
        System.out.println("Edges: " + network.edges().size());
        System.out.println("Nodes: " + network.nodes().size());

        while (!network.successors(nodeMap.get("you")).isEmpty()) {
            for(final MyNode n : network.nodes()) {
                if (network.successors(n).isEmpty()) {
                    network.predecessors(n).forEach(p -> p.value += n.value);
                    network.removeNode(n);
                    break;
                }
            }
        }

        System.out.println(nodeMap.get("you").value);
        System.out.println("Edges: " + network.edges().size());
        System.out.println("Nodes: " + network.nodes().size());
    }

    private record MyEdge(String start, String end, int value) {
    }

    private static class MyNode {
        String name;
        int value;

        MyNode(final String name, final int value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public boolean equals(final Object o) {
            return this.name.equals(((MyNode)o).name);
        }
    }
}
