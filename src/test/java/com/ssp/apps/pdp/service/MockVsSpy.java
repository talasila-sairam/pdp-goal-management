package com.ssp.apps.pdp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MockVsSpy {

  @Mock
  private List<String> mockList = new ArrayList<String>();

  @Spy
  private List<String> spyList = new ArrayList<String>();

  @Test
  public void test() {
    mockList.add("Hi");
    spyList.add("Hi");

    String value1 = mockList.get(0);
    String value2 = spyList.get(0);

    System.out.println(value1);
    System.out.println(value2);
  }

  @Test
  public void test1() {
    mockList.add("Hi");
    spyList.add("Hi");
    when(mockList.get(0)).thenReturn("Hello");
    when(spyList.get(0)).thenReturn("Haiiiii");
    String value1 = mockList.get(0);
    String value2 = spyList.get(0);

    System.out.println(value1);
    System.out.println(value2);
  }
}
