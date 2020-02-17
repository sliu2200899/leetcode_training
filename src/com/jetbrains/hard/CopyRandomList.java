package com.jetbrains.hard;

import com.jetbrains.innerStructure.Node;

import java.util.HashMap;
import java.util.Map;

public class CopyRandomList {
    public static Node copyRandomList(Node head) {
        if (head == null) return null;

        // in the first round, should create the node and store it in the map
        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            Node node = new Node(cur.val);
            map.put(cur, node);

            cur = cur.next;
        }

        // in the second round, should establish the next and random relationship along the way
        cur =  head;
        while (cur != null) {

            Node newNode = map.get(cur);
            newNode.next = map.get(cur.next);
            newNode.random = map.get(cur.random);

            cur = cur.next;
        }

        return map.get(head);
    }

}
