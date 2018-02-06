package ru.spb.yakovlev;

public class Tree {
    private static int MAX_DEPTH = 6;

    private TreeNode root;


    public TreeNode getNode(int key) {
        TreeNode current = root;
        while (current.key != key) {
            if (key < current.key)
                current = current.leftChild;
            else
                current = current.rightChild;

            if (current == null) {
                return null;
            }
        }
        return current;
    }

    public int insert(int key) {
        int level = 1;
        TreeNode node = new TreeNode(key);
        if (root == null) {
            root = node;
            return level;
        } else {
            TreeNode current = root;
            TreeNode parent;
            while (level < MAX_DEPTH) {
                level++;
                parent = current;
                if (key == current.key) return 0;
                else if (key < current.key) {
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = node;
                        return level;
                    }
                } else {
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = node;
                        return level;
                    }
                }
            }
        }
        return -1;
    }

    public int getMinDepth() {
        if (root == null) {
            System.out.println("Tree is empty");
            return 0;
        }
        return getNextDepth(root, 0);
    }

    private int getNextDepth(TreeNode current, int depth) {
        if (current.leftChild == null || current.rightChild == null) return depth;
        else {
            depth++;
            return Math.min(getNextDepth(current.leftChild, depth), getNextDepth(current.rightChild, depth));
        }
    }


    private void inOrderTravers(TreeNode currentNode) {
        if (currentNode != null) {
            inOrderTravers(currentNode.leftChild);
            System.out.println(currentNode);
            inOrderTravers(currentNode.rightChild);
        }
    }

    public void displayTree() {
        inOrderTravers(root);
    }

    public TreeNode min() {
        TreeNode current = root;
        TreeNode last = null;
        while (current != null) {
            last = current;
            current = current.leftChild;
        }
        return last;
    }

    public boolean delete(int key) {
        TreeNode current = root;
        TreeNode parent = root;
        boolean isLeftChild = true;

        // search
        while (current.key != key) {
            parent = current;
            if (key < current.key) {
                isLeftChild = true;
                current = current.leftChild;
            } else {
                isLeftChild = false;
                current = current.rightChild;
            }
            if (current == null)
                return false;
        }

        // leaf
        if (current.leftChild == null && current.rightChild == null) {
            if (current == root)
                root = null;
            else if (isLeftChild)
                parent.leftChild = null;
            else
                parent.rightChild = null;
            // if only one child = make him successor
        } else if (current.rightChild == null) {
            if (isLeftChild)
                parent.leftChild = current.leftChild;
            else
                parent.rightChild = current.leftChild;
        } else if (current.leftChild == null) {
            if (isLeftChild)
                parent.leftChild = current.rightChild;
            else
                parent.rightChild = current.rightChild;
            // if node has two children
        } else {
            TreeNode successor = getSuccessor(current);
            if (current == root)
                root = successor;
            else if (isLeftChild)
                parent.leftChild = successor;
            else
                parent.rightChild = successor;
            successor.leftChild = current.leftChild;
        }
        return true;
    }

    private TreeNode getSuccessor(TreeNode node) {
        TreeNode parent = node;
        TreeNode successor = node;
        TreeNode current = node.rightChild;

        while (current != null) {
            parent = successor;
            successor = current;
            current = current.leftChild;
        }
        if (successor != node.rightChild) {
            parent.leftChild = successor.rightChild;
            successor.rightChild = node.rightChild;
        }
        return successor;

    }

    private class TreeNode {
        int key;
        TreeNode leftChild;
        TreeNode rightChild;

        TreeNode(int key) {
            this.key = key;
            leftChild = null;
            rightChild = null;
        }
    }

}
