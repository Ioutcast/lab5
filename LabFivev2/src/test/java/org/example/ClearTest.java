package org.example;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;


public class ClearTest {
    CommandMenu commandMenu = new CommandMenu();

    @Test
    public void ClearTest() throws Exception {
        Assert.assertEquals(CommandMenu.State.OK, commandMenu.commandManager("clear", new ArrayList<>(), true));
        Assert.assertEquals(0, commandMenu.getMapCollection().size());
    }
}
