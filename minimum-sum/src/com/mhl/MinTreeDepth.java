package com.mhl;

/**
 * Created by minghuiliu on 29/7/18.
 */

/**
 * This is a BINARY TREE
 */
public class MinTreeDepth {

  /* Java implementation to find minimum depth
   of a given Binary tree */

  /* Class containing left and right child of current
  node and key value*/
  public static class Node
  {
    int data;
    Node left, right;
    public Node(int item)
    {
      data = item;
      left = right = null;
    }
  }

   public static class BinaryTree {
    //Root of the Binary Tree
    Node root;

    int minimumDepth()
    {
      return minimumDepth(root);
    }

    /* Function to calculate the minimum depth of the tree */
    int minimumDepth(Node root)
    {
      // Corner case. Should never be hit unless the code is
      // called on root = NULL
      if (root == null)
        return 0;

      // Base case : Leaf Node. This accounts for height = 1.
      if (root.left == null && root.right == null)
        return 1;

      // If left subtree is NULL, recur for right subtree
      if (root.left == null)
        return minimumDepth(root.right) + 1;

      // If right subtree is NULL, recur for left subtree
      if (root.right == null)
        return minimumDepth(root.left) + 1;

      return Math.min(minimumDepth(root.left),
          minimumDepth(root.right)) + 1;
    }


  }

  /* Driver program to test above functions */
  public static void main(String args[])
  {
    BinaryTree tree = new BinaryTree();
    tree.root = new Node(1);
    tree.root.left = new Node(2);
    tree.root.right = new Node(3);
    tree.root.left.left = new Node(4);
    tree.root.left.right = new Node(5);

    System.out.println("The minimum depth of "+
        "binary tree is : " + tree.minimumDepth());
  }

}
