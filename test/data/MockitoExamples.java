package data;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class MockitoExamples {
    @Test
    public void mockList() {
        List mockedList = mock(MyList.class);
        mockedList.add("one");
        mockedList.clear();

        verify(mockedList).add("one");
        verify(mockedList).clear();
        assertThat(mockedList.size(), equalTo(0));
    }

    @Test
    public void partialMock() {
        List<String> spiedList = spy(new MyList<String>());
//        when(spiedList.size()).thenReturn(100);
        doReturn(100).when(spiedList).size();
        spiedList.add("one");
        spiedList.add("two");

        assertThat(spiedList.get(0), equalTo("one"));
        assertThat(spiedList.size(), equalTo(100));
        verify(spiedList, times(1)).size();
    }

    static class MyList<T> extends LinkedList<T> {
        @Override
        public int size() {
            return super.size();
        }

        @Override
        public boolean add(T t) {
            return super.add(t);
        }

        @Override
        public T get(int index) {
            return super.get(index);
        }
    }
}
