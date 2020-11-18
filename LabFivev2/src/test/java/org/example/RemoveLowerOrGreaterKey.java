package org.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


public class RemoveLowerOrGreaterKey {


    private static CommandMenu commandMenu;

//    @BeforeClass
//    public static void beforeClass() throws Exception {
//          инициализация конкретного объекта CommandMenu
//        commandMenu = new CommandMenu();
//    }

    @Before
    public void setUp() throws Exception {
        commandMenu = new CommandMenu();
    }

    @Test
    public void RemoveGreaterKey() throws Exception {
        ArrayList<String> arguments = new ArrayList<>();
        arguments.add("3");
        commandMenu.getMapCollection().put(1, new Worker());
        commandMenu.getMapCollection().put(2, new Worker());
        commandMenu.getMapCollection().put(3, new Worker());
        commandMenu.getMapCollection().put(4, new Worker());
        Assert.assertEquals(CommandMenu.State.OK, commandMenu.commandManager("remove_greater_key 3", arguments, true));
    }

    @Test
    public void RemoveLowerKey() throws Exception {
        ArrayList<String> arguments = new ArrayList<>();
        arguments.add("3");

        commandMenu.getMapCollection().put(1, new Worker());
        commandMenu.getMapCollection().put(2, new Worker());
        commandMenu.getMapCollection().put(3, new Worker());
        commandMenu.getMapCollection().put(4, new Worker());
        Assert.assertEquals(CommandMenu.State.OK, commandMenu.commandManager("remove_lower_key 3", arguments, true));
    }

    @Test
    public void RemoveLowerWrongKey() throws Exception {
        commandMenu.getMapCollection().put(1, new Worker());
        commandMenu.getMapCollection().put(2, new Worker());
        commandMenu.getMapCollection().put(3, new Worker());
        commandMenu.getMapCollection().put(4, new Worker());
        Assert.assertEquals(CommandMenu.State.ERROR, commandMenu.commandManager("remove_lower_key s", new ArrayList<>(), true));
    }
}
