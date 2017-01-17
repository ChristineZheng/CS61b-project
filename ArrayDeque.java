public class ArrayDeque<Item> {
    private Item[] array;
    private int size;
    private Item item;
    private int back;
    private int front;

    public ArrayDeque() {
        size = 0;
        front = 0;
        array = (Item []) new Object[8];
        back = size;
    }

    private void resize(int capacity) {
        Item[] newArray = (Item []) new Object[capacity];
        if (front < back) {
            System.arraycopy(array, 0, newArray, 0, size);
        } else {
            System.arraycopy(array, front, newArray, 0, size - front);
            System.arraycopy(array, 0, newArray, size - front, front);
        }
        array = newArray;
        front = 0;
        back = size - 1;
    }


    public void addFirst(Item x) {
        if(size == 0){
            size += 1;
            array[front] = x;
            //back = front;
        } else {
            if(size >= array.length) {
                resize(size * 4);
                front = array.length - 1;
            } else {
                if(front == 0) {
                    front = array.length - 1;
                } else if(front <= back) {
                    front -= 1;
                } else {
                    front -= 1;
                }
            }
            array[front] = x;
            size += 1;
        }
    }


    public void addLast(Item x) {
        if(size >= array.length){
            resize(size * 4);
            back += 1;
            array[back] = x;
        } else if(size == 0){
            array[back] = x;
        } else {
            if(back == array.length - 1){
                back = 0;
            } else {
                back += 1;
            }
            array[back] = x;
        }
        size += 1;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public int size(){
        return size;
    }


    public void printDeque() {
        if (size == 0) {
            return;
        }
        if (back < front) {
            for (int i = front; i <= array.length - 1; i++) {
                System.out.print(array[i] + " ");
            }
            for (int j = 0; j <= back; j++) {
                System.out.print(array[j] + " ");
            }
        } else {
            for (int k = front; k <= back; k++) {
                System.out.print(array[k] + " ");
            }
        }
    }


    public Item removeFirst(){
        if(size == 0){
            return null;
        }
        Item Temp = array[front];
        array[front] = null;
        if((front < back) || ((front > back) && (front != array.length - 1))) {
            front += 1;
        } else if(front == back){
            front = front;
        } else if(front == array.length - 1) {
            front = 0;
        }
        size -= 1;
        /* if(((float) (size / array.length)) < 0.25){
            resize(array.length / 2);
        }  */
        return Temp;
    }


    public Item removeLast(){
        if(size == 0){
            return null;
        }
        Item Temp = array[back];
        array[back] = null;
        if(size != 1){
            if(back == 0){
                back = array.length - 1;
            } else {
                back -= 1;
            }
        }
        size -= 1;
        /* if(((float) (size / array.length)) < 0.25){
            resize(array.length / 2);
        } */
        return Temp;
    }


    public Item get(int index) {
        if (size == 0) {
            return null;
        } else if (index == 0) {
            return array[front];
        } else if (back < front) {
            if (index < array.length - front) {
                return array[index + front];
            } else {
                return array[index - (array.length - front)];
            }
        } else {
            return array[index + front];
        }
    }
}