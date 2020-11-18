package org.example;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;



public class MenuTest {
    CommandMenu commandMenu = new CommandMenu();

    @Test
    public void NotFoundCommand() throws Exception {
        Assert.assertEquals(CommandMenu.State.NOT_FOUND_COMMAND, commandMenu.commandManager("abcdef", new ArrayList<>(), true));
    }
}
