import java.util.Optional

/** 
 * Keep track of the size of the object.
 * Currently tracks only one dimension, but will need to be 2 dims in the future.
 */
public class Size {
    private int _inches;

    /** Constructor taking one dimension size */
    public Size( int inches) {
        _inches = inches;
    }

    /** Get the size of this object */
    public int getSize() {
        return _inches;
    }
} // Size

/**
 * An item that may be stored in the refer.
 */
public class Item {
    private Size   _size;
    private String _name;
    private int    _id;

    /** Constructor - All data passed in constructor. Data is unchangeable after that. */
    public Item( int id, String name, Size size ) {
        assert( size != null );
        assert( name != null );

        _size = size;
        _name = name;
        _id   = id;
    }

    /** id gettor */
    public int getId() {
        return _id;
    }

    /** name gettor */
    public String getName() {
        return _name;
    }
  
    /** size gettor */
    public Size getSize() {
        return _size;
    }

    /** are the two item equal? */
    public boolean equals( Item item ) {
        return item.getId() == getId();
    }
} // Item

/**
 * Enumeration of the different shelf sizes.
 */
public enum ShelfSize {
    SMALL {
        private _size Size = new Size(18);
        public Size getSize() {
            return _size;
        }
    }, // small
    MEDIUM { 
        private _size Size = new Size(36);
        public Size getSize() {
            return _size;
        }
    }, // medium
    LARGE {
        private _size Size = new Size(54);
        public Size getSize() {
            return _size;
        }
    }; // large
}

/**
 * Class representing a shelf in the fridge.
 */
public class Shelf {
    private ShelfSize _shelfSize;
    private List<Item> _items = new List<Item>();

    /**
     * Constructor - takes the size of the shelf.
     */
    public Shelf( ShelfSize shelfSize ) {
        asset( shelfSize != null )
        _shelfSize = shelfSize;
    }

    /**
     * Does this shelf contain the given item?
     * Return item if it does, or an empty optional if it does not
     */
    public boolean containsItem( Item item ) {
        assert( item != null );
        ListIterator<Item> li = _items.listIterator();
        int idx = li.getIndexOf(item);
        return (idx>=0);
    }

    /**
     * Get the item out off of the shelf.
     * Returns the item (if found) or an empty optional if it wasn't in there.
     */
    public Optional<Item> removeItem( Item item ) {
        assert( item != null );
        if (containsItem(item) ) {
            ListIterator<Item> li = _items.listIterator();
	    int idx = li.getIndexOf(item);
            return li.remove(idx);
        } else {
            return Optional<Item>.empty()
        }
    }

    /**
     * Get the amount of space remaining on the shelf.
     * Compute the space remaining on the shelf after the item list is subtracted.
     */
    public int getSpaceRemaining() {
        int spaceLeft = _shelfSize.getSize();
        ListIterator<Item> li = _items.listIterator();
        while ( li.hasNext() ) {
            Item nextItem = li.next();
            spaceLeft -= nextItem.getSize();
        }
        return (spaceLeft>0) ? spaceLeft : 0;
    }

    /** Does this shelf have enough space for the given item? */
    public boolean hasEnoughSpace( Item item ) {
        assert( item != null );
        return ( getSpaceRemaining()  >  item.getSize() );
    }

    /** Put the item on the shelf */
    public void putItem( Item item ) {
        assert( item != null );
        assert( hasEnoughSpace(item) );

        _items.append( item );
    }
} // Shelf

public class Refer
{
    private List<Shelf> _shelves = new List<Shelf>;

    /** Refridgerator constructor. Takes a list of shelves */
    public Refer( List<Shelf> shelves ) {
        assert( shelves != null );

        ListIterator<Shelf> li = shelves.listIterator();
        while ( li.hasNext() ) {
            shelves.append( li.next() );
        }
    }

    /** Get the item out of the fridge. */
    public Item get( Item item ) {
        assert( item != null );

        // Look for the item shelf by shelf.
        ListIterator<Shelf> lis = _shelves.listIterator();
        while ( lis.hasNext() ) {
            Shelf currentShelf = lis.next();
            if ( currentShelf.containsItem( item ) ) {
                return currentShelf.removeItem();
            } // if
        } // while lis
        return Optional<Item>.empty();
    }

    /**
     * Put the item into the fridge.
     * Look for the shelf with the least amount of space (that also has enough space)
     * to save space for larger items
     */
    public void put( Item item ) {
        assert( item != null );
        
        Optional<Shelf> bestShelf = Optional.empty();
        ListIterator<Shelf> lis = _shelves.listIterator();

        while ( lis.hasNext() ) {
            Shelf currentShelf = li.next();
            if ( currentShelf.hasEnoughSpace( item ) ) {
                if ( bestShelf.isEmpty() ) {
                    bestShelf = currentShelf;
                } else if ( currentShelf.getSpaceRemaining() < bestShelf.getSpaceRemaining() ) {
                    bestShelf = currentShelf;
                } // Look for shelf with least amount of space remaining, but still has space
                  // for the current item
            }  // if shelf has enough space
        } // while

        if ( bestShelf.isEmpty() ) {
            throw new ReferFullExcepation( "Sorry, that item does not fit." );
        } 
    }
} // Refer
