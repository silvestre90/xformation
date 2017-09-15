package mineSweeper;

import org.junit.Test;

import static org.junit.Assert.*;

// Ctrl Shift T
public class MineSweeperTest {

    MineSweeper mineSweeper = new MineSweeperImpl();

    @Test
    public void shouldAcceptCorrectMineField() {
        mineSweeper.setMineField("*...\n..*.\n....");
    }

    @Test
    public void shouldAcceptEmptyMineField() {
        mineSweeper.setMineField("");
    }

    @Test
    public void shouldAcceptSingleRow() {
        mineSweeper.setMineField("......");
    }
    @Test
    public void shouldAcceptZeroLenghtRows() {
        mineSweeper.setMineField("\n\n\n");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowsIllegalArgumentExceptionForNonRectangleMineField() {
        mineSweeper.setMineField("...\n..\n.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowsIllegalArgumentExceptionForNonLegalCharacters() {
        mineSweeper.setMineField(".a.\n..\n.");

    }

    @Test
    public void shouldResolveEmptyMineField() throws Exception {
        // given
        mineSweeper.setMineField("");

        // when
        String result = mineSweeper.getHintField();

        // then
        assertTrue("".equals(result));
    }

    @Test
    public void shouldResolveCorrectly() throws Exception {
        // given
//        mineSweeper.setMineField("....\n..*.\n....");
//        mineSweeper.setMineField(".*..*\n..*..\n.**..");
        mineSweeper.setMineField("***\n..*\n**.");


        // when
        String result = mineSweeper.getHintField();

        // then
//        assertTrue("0111\n01*1\n0111".equals(result));
//        assertTrue("1*22*\n24*31\n1**20".equals(result));
        assertTrue("***\n46*\n**2".equals(result));
    }


//    @Test
//    public void shouldSetGridCorrectly() throws Exception {
//        // given
//        String mineField = "....\n..*.\n....";
//
//
//        // when
//        String result = mineSweeper.setMineField("....\n..*.\n....");
//
//        // then
//        assertTrue("0111\n01*1\n0111".equals(result));
//    }
}