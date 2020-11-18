package org.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.example.CommandMenu.State;
import static org.example.CommandMenu.isAutomatic;

public class UpdateTest {

    CommandMenu commandMenu = new CommandMenu();
    @Before
    public void setUp() throws Exception {
        commandMenu.getMapCollection().clear();
        isAutomatic = true;
        Worker worker = new Worker();
        worker.setName("Test");
        commandMenu.getMapCollection().put(1, worker);
    }

    @Test
    public void PositiveWork() throws Exception {
        ArrayList<String> arguments = new ArrayList<>();
        arguments.add("abc");
        arguments.add("200");
        arguments.add("100");
        arguments.add("42");
        arguments.add("16/08/2016");
        arguments.add("1986-04-08 12:30");
        arguments.add("RECOMMENDED_FOR_PROMOTION");
        arguments.add("NO");
        arguments.add("1");
        arguments.add("PUBLIC");
        arguments.add("street");
        arguments.add("zipcode");
        arguments.add("1");
        arguments.add("2");
        arguments.add("locName");
        Assert.assertEquals(State.OK, commandMenu.commandManager("update 1", arguments, true));
        System.out.println(commandMenu.getMapCollection().toString());
    }

    @Test
    public void NegativeWork() throws Exception {
        ArrayList<String> arguments = new ArrayList<>();
        arguments.add("");        arguments.add("1");                                        arguments.add("name");
        arguments.add("");        arguments.add("abc");  arguments.add("abc");                arguments.add("200");        arguments.add("100");
        arguments.add("");        arguments.add("abc");                                     arguments.add("42");
        arguments.add("");        arguments.add("16/082016");                              arguments.add("16/08/2016");
        arguments.add("");        arguments.add("1986-04-08 1230");                        arguments.add("1986-04-08 12:30");
        arguments.add("");        arguments.add("aa");                                     arguments.add("RECOMMENDED_FOR_PROMOTION");
                                                             arguments.add("NO");
        arguments.add("");        arguments.add("NO");                                  arguments.add("1");
        arguments.add("");        arguments.add("NO");                                   arguments.add("PUBLIC");
        arguments.add("");        arguments.add("16/082016");                          arguments.add("street");
        arguments.add("");        arguments.add("16/082016");                          arguments.add("zipcode");
        arguments.add("");        arguments.add("no");                                arguments.add("1");
        arguments.add("");        arguments.add("NO");                                    arguments.add("2");
        arguments.add("");        arguments.add("16082016");                          arguments.add("locName");
        Assert.assertEquals(State.OK, commandMenu.commandManager("update 1", arguments, true));
        System.out.println(commandMenu.getMapCollection().toString());
    }
}
