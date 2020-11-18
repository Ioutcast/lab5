package org.example;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;


public class ShowInfoTest {
    CommandMenu commandMenu = new CommandMenu();
    @Test
    public void ShowTestOk() throws Exception {
        commandMenu.getMapCollection().put(1, new Worker());
        Assert.assertEquals(CommandMenu.State.OK, commandMenu.commandManager("show", new ArrayList<>(), true));
    }

    @Test
    public void InfoTestOk() throws Exception {
        Assert.assertEquals(CommandMenu.State.OK, commandMenu.commandManager("info", new ArrayList<>(), true));

    }

}
