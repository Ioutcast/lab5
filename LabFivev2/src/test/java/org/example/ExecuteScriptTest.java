package org.example;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;



public class ExecuteScriptTest {
    CommandMenu commandMenu = new CommandMenu();
    @Test
    public void ExecuteScriptWrongName() throws Exception {
        Assert.assertEquals(CommandMenu.State.OK, commandMenu.commandManager("execute_script a", new ArrayList<>(), true));
    }

    @Test
    public void ExecuteScrip() throws Exception {
        Assert.assertEquals(CommandMenu.State.OK, commandMenu.commandManager("execute_script Script", new ArrayList<>(), true));
    }

}
