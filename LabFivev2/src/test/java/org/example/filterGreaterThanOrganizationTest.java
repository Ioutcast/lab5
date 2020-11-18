package org.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


public class filterGreaterThanOrganizationTest {
    CommandMenu commandMenu = new CommandMenu();
    @Before
    public void setUp() throws Exception {

        ArrayList<String> arguments = new ArrayList<>();
        Worker worker = new Worker();
        worker.setName("Test");
        commandMenu.getMapCollection().put(1, worker);
        arguments.add("abc");
        arguments.add("200");
        arguments.add("100");
        arguments.add("42");
        arguments.add("16/08/2016");
        arguments.add("1986-04-08 12:30");
        arguments.add("RECOMMENDED_FOR_PROMOTION");
        arguments.add("NO");
        arguments.add("123");
        arguments.add("PUBLIC");
        arguments.add("street");
        arguments.add("zipcode");
        arguments.add("1");
        arguments.add("2");
        arguments.add("locName");
        commandMenu.commandManager("update 1", arguments, true);

    }

    @Test
    public void filterGreaterThanOrganizationTestInput() throws Exception {
        ArrayList<String> arguments = new ArrayList<>();
        arguments.add("NO");
        arguments.add("1");
        arguments.add("PUBLIC");
        arguments.add("street");
        arguments.add("zipcode");
        arguments.add("2");
        arguments.add("2");
        arguments.add("locName");
        Assert.assertEquals(CommandMenu.State.OK, commandMenu.commandManager("filter_greater_than_organization", arguments, true));

    }
}
