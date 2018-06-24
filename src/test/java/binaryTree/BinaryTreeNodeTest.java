package binaryTree;

import common.SchluesselWertPaar;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by michelbrueger on 24.06.18.
 */
public class BinaryTreeNodeTest {
    BinaryTreeNode btn0;
    BinaryTreeNode btn1;
    BinaryTreeNode btn2;
    BinaryTreeNode btn3;
    BinaryTreeNode btn4;


    @Before
    public void setUp() throws Exception {
        btn1 = new BinaryTreeNode(11);
        btn2 = new BinaryTreeNode(22);
        btn3 = new BinaryTreeNode(33);
        btn3.insert(44);
        btn3.insert(55);
        btn3.insert(50);
        btn3.insert(22);
        btn3.insert(1);
        btn3.insert(2);
        btn3.insert(4);
        btn3.insert(3);



    }

    @Test
    public void depth() throws Exception {
        assertEquals(0, btn1.getDepth(0));
        btn1.insert(22);
        assertEquals(1,btn1.getDepth(0));
        assertEquals(5, btn3.getDepth(0));
    }

    @Test
    public void insert() throws Exception {
        assertEquals(0, btn1.getHeight());
        btn1.insert(15);
        assertEquals(0, btn1.getHeight());
        assertEquals(15, btn1.getRightSuccessor().getData());
        assertEquals(1, btn1.getRightSuccessor().getHeight());
        btn1.insert(21);
        assertEquals(0, btn1.getHeight());
        assertEquals(15, btn1.getRightSuccessor().getData());
        assertEquals(1, btn1.getRightSuccessor().getHeight());
        assertEquals(2, btn1.getRightSuccessor().getRightSuccessor().getHeight());
        assertEquals(null, btn1.getLeftSuccessor());
        btn1.insert(5);
        assertEquals(0, btn1.getHeight());
        assertEquals(15, btn1.getRightSuccessor().getData());
        assertEquals(1, btn1.getRightSuccessor().getHeight());
        assertEquals(2, btn1.getRightSuccessor().getRightSuccessor().getHeight());
        assertEquals(5, btn1.getLeftSuccessor().getData());
        assertEquals(1, btn1.getLeftSuccessor().getHeight());
        btn1.insert(7);
        assertEquals(0, btn1.getHeight());
        assertEquals(15, btn1.getRightSuccessor().getData());
        assertEquals(1, btn1.getRightSuccessor().getHeight());
        assertEquals(2, btn1.getRightSuccessor().getRightSuccessor().getHeight());
        assertEquals(5, btn1.getLeftSuccessor().getData());
        assertEquals(1, btn1.getLeftSuccessor().getHeight());
        assertEquals(7, btn1.getLeftSuccessor().getRightSuccessor().getData());
        assertEquals(2, btn1.getLeftSuccessor().getRightSuccessor().getHeight());
        btn1.insert(6);
        assertEquals(0, btn1.getHeight());
        assertEquals(3, btn1.getLeftSuccessor().getRightSuccessor().getLeftSuccessor().getHeight());

//        assertFalse(btn1.isBalanced());




    }

    @Test
    public void isBalanced() throws Exception {
        assertTrue(btn2.isBalanced());
        btn2.insert(33);
        assertTrue(btn2.isBalanced());
        btn2.insert(44);
        assertFalse(btn2.isBalanced());
        assertEquals(null, btn2.getLeftSuccessor());
        assertFalse(btn3.isBalanced());

    }


    @Test
    public void isLeaf() throws Exception {
    }

}