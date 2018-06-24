package binaryTree;

import common.SchluesselWertPaar;

/**
 * Created by michelbrueger on 24.06.18.
 */
public class BinaryTreeNode {

    private BinaryTreeNode leftSuccessor;
    private BinaryTreeNode rightSuccessor;
    private BinaryTreeNode parent;
    private int data;
    private int height;



    //Konstruktoren
    // Leerer Baum Konstruktor
    public BinaryTreeNode(){
        this.leftSuccessor = null;
        this.rightSuccessor = null;
        this.height = 0;
        this.data = 0;
    }
    // BlattKonstruktor
    public BinaryTreeNode(int data){
        this.data = data;
        this.leftSuccessor = null;
        this.rightSuccessor = null;
        this.height = 0;
    }
    //Interner Knoten Konstruktor
    public BinaryTreeNode(int data, BinaryTreeNode leftSuccessor, BinaryTreeNode rightSuccessor){
        this.data = data;
        this.leftSuccessor = leftSuccessor;
        this.rightSuccessor = rightSuccessor;
        this.height = 1 + Math.max(leftSuccessor.getHeight(), rightSuccessor.getHeight());
    }

    public BinaryTreeNode getLeftSuccessor() {
        return leftSuccessor;
    }
    public BinaryTreeNode getRightSuccessor() {
        return rightSuccessor;
    }

    public int getHeight() {
        return height;
    }


    public int getData() {
        return data;
    }

    public void setLeftSuccessor(BinaryTreeNode leftSuccessor) {
        this.leftSuccessor = leftSuccessor;
    }

    public void setRightSuccessor(BinaryTreeNode rightSuccessor) {
        this.rightSuccessor = rightSuccessor;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setParentHeight(int height){
        if(this.parent == null) return;
        if(this.parent.leftSuccessor == null || this.parent.rightSuccessor == null){
            this.parent.setHeight(height);
        } else {
            this.parent.setHeight(1 + Math.max(this.parent.leftSuccessor.getHeight(), this.parent.rightSuccessor.getHeight()));
        }
        if(this.parent.parent != null){
            this.parent.setParentHeight(height +1);
        }
    }

    /**
     * Inserts new data Objects into this tree (no duplicates)
     * @param data Object to be inserted (only int, better no '0')
     */
    public void insert(int data){
        if(this.data == data) return;
        else {
            if (data < this.data){
                if (leftSuccessor == null){
                    leftSuccessor = new BinaryTreeNode(data);
                    leftSuccessor.setHeight(this.height+1);
                } else {
                    leftSuccessor.insert(data);
                }
            } else {
                if (rightSuccessor == null){
                    rightSuccessor = new BinaryTreeNode(data);
                    rightSuccessor.setHeight(this.height+1);
                } else {
                    rightSuccessor.insert(data);
                }
            }
        }
    }

/*
public void insert(int data){
if (this.data == data) return;
else {
if (data < this.data){
if (leftSuccessor == null){
leftSuccessor = new BinaryTreeNode(data);
leftSuccessor.setParent(this);
leftSuccessor.setHeight(0);
leftSuccessor.setParentHeight(1);
} else {
leftSuccessor.insert(data);
}
} else {
if (rightSuccessor == null){
rightSuccessor = new BinaryTreeNode(data);
rightSuccessor.setParent(this);
rightSuccessor.setHeight(0);
rightSuccessor.setParentHeight(1);
} else {
rightSuccessor.insert(data);
}
}
}
}
*/

    private BinaryTreeNode getParent() {
        return this.parent;
    }

    private void setParent(BinaryTreeNode parent){
        this.parent = parent;
    }

    private void setHeight(int height) {
        this.height = height;
    }

    /**
     * Computes if Binary Node (Tree) is height balanced (heights of subtrees in every Node differ by less than two)
     * @return TRUE if Node is balanced, FALSE if not.
     */
    public boolean isBalanced(){
        if (isLeaf()){
            return true;
        } else if (leftSuccessor == null) {
            return rightSuccessor.getDepth(0) < 1;
        } else if (rightSuccessor == null) {
            return leftSuccessor.getDepth(0) < 1;
        } else {
            int indicator = leftSuccessor.getDepth(0) - rightSuccessor.getDepth(0);
            return (indicator < 1) || (indicator > -1) && rightSuccessor.isBalanced() && leftSuccessor.isBalanced();
        }
    }

    public int getDepth(int depth) {
        if (this.isLeaf()){
            return depth;
        } else {
            depth += 1;
        }
        if(leftSuccessor != null && rightSuccessor != null){
            return  Math.max(leftSuccessor.getDepth(depth),rightSuccessor.getDepth(depth));
        } else if (leftSuccessor != null){
            return  leftSuccessor.getDepth(depth);
        } else if (rightSuccessor != null){
            return  rightSuccessor.getDepth(depth);
        } else return depth;

    }

//    public boolean isBalanced(){
////        if(this.height == 0) return true;
//        int leftHeight, rightHeight;
//        if(this.leftSuccessor == null) {
//            leftHeight = 0;
//        } else {
//            leftHeight = this.leftSuccessor.getHeight();
//        }
//        if(this.rightSuccessor == null){
//            rightHeight = 0;
//        } else {
//            rightHeight = this.rightSuccessor.getHeight();
//        }
////        int indikator = this.leftSuccessor.getHeight() - this.rightSuccessor.getHeight();
//        int indikator = leftHeight - rightHeight;
//        if(indikator > 1 || indikator < -1) return false;
//        if(leftSuccessor != null){
//            leftSuccessor.isBalanced();
//        }
//        if(rightSuccessor != null){
//            rightSuccessor.isBalanced();
//        }
//        return true;
//    }

    /**
     * Computes if Node is a Leaf (has no children)
     * @return TRUE if node is a leaf, FALSE if not.
     */
    public boolean isLeaf(){
        return (this.leftSuccessor == null && this.rightSuccessor == null);
    }
}
