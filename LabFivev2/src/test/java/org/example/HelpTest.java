package org.example;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;



public class HelpTest {
    CommandMenu commandMenu = new CommandMenu();
    @Test(expected = Exception.class)
    public void CommandHelpWithException() throws Exception {
        Assert.assertEquals(CommandMenu.State.ERROR, commandMenu.commandManager("help 2", new ArrayList<>(), true));
    }

    @Test
    public void CommandHelp() throws Exception {
        Assert.assertEquals(CommandMenu.State.OK, commandMenu.commandManager("help", new ArrayList<>(), true));
    }
}
