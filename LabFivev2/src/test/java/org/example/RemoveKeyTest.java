package org.example;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;


public class RemoveKeyTest {
    CommandMenu commandMenu = new CommandMenu();
    @Test
    public void RemoveKeyTestWrongInput() throws Exception {
        Assert.assertEquals(CommandMenu.State.ERROR, commandMenu.commandManager("remove_key фис", new ArrayList<>(), false));
    }

    @Test
    public void RemoveKeyTest() throws Exception {
        commandMenu.getMapCollection().put(1, new Worker());
        Assert.assertEquals(CommandMenu.State.OK, commandMenu.commandManager("remove_key 1", new ArrayList<>(), false));
        Assert.assertEquals(null, commandMenu.getMapCollection().get(1));
    }

    @Test
    public void RemoveKeyTestInput() throws Exception {
        Assert.assertEquals(CommandMenu.State.ERROR, commandMenu.commandManager("remove_key 1", new ArrayList<>(), false));

    }


}
