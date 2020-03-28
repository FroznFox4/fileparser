package com.example.demo.Services.Components.StringParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Arrays;

@Component
public class StringParser {
    /**
     *  String parser
     * @param file String containing structure
     * @return Result JSON - String
     */
    public static String fileToArray(String file) {
        try {
            DefaultMutableTreeNode structure = parsFileToTree(file);
            return depthSearchNLR(structure);
        } catch (Exception e) {
            return Arrays.toString(e.getStackTrace());
        }
    }

    /**
     *  Turning a structure into a row in a tree
     * @param line String containing structure
     * @return DefaultMutableTreeNode with structure
     * @throws Exception Error in structure
     */
    private static DefaultMutableTreeNode parsFileToTree(String line) throws Exception {
        DefaultMutableTreeNode result = new DefaultMutableTreeNode("Start");
        DefaultMutableTreeNode parent = result;
        StringBuilder data = new StringBuilder();
        int delta = 0;
        int countSymbols = 0;
        int cursorLine = 0;
        int symbol = line.indexOf('#');
        line = line.substring(symbol);
        symbol = 0;

        line = line.replaceAll("#\\s+", "#").replaceAll("\\s+#", "#");

        while (cursorLine != line.length() - 1) {
            if (line.charAt(cursorLine) == '#') {
                countSymbols += 1;
                cursorLine++;
            } else if (countSymbols != 0) {
                if (symbol + 1 >= countSymbols) {
                    do {
                        data.append(line.charAt(cursorLine));
                        cursorLine++;
                    } while (line.charAt(cursorLine) != '#' && (cursorLine != line.length() - 1));
                    DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(data.toString());
                    delta = countSymbols - symbol;
                    if (countSymbols == 1) {
                        result.add(newNode);
                    } else if (delta == 0) {
                        parent.getPreviousNode().add(newNode);
                    } else if (delta == 1) {
                        parent.add(newNode);
                    } else if (-delta > 0) {
                        for (int i = 0; i <= (-delta); i++) {
                            parent = parent.getPreviousNode();
                        }
                        parent.add(newNode);
                    }
                    parent = newNode;
                    data.delete(0, data.length());
                    symbol = countSymbols;
                    countSymbols = 0;
                } else {
                    throw new Exception("Fail");
                }
            }
        }
        return result;
    }

    /**
     * Serialize tree to Gson object
     * @param tree Tree who need serialize
     * @return Gson object
     */
    private static String depthSearchNLR(DefaultMutableTreeNode tree) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(DefaultMutableTreeNodeTypeAdapter.FACTORY)
                .setPrettyPrinting()
                .create();
        return gson.toJson(tree);
    }
}
