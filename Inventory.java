import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Comparator;

public class Inventory {
	// Item, Qty
	private List<InventoryItem> items;
	private int saga;

	public Inventory() {
		this.items = new ArrayList<InventoryItem>();
	}

	public List<InventoryItem> getItems() { return this.items; }
	
	// mengembalikan banyak total item
	public int getItemTotalCount() { 
		this.saga = 0;
		for (InventoryItem i: items) {
			this.saga += i.getQty();
		}
		return this.saga;
	}
	
	public boolean isi(Item item) {
		for (InventoryItem i: items) {
			if (i.getItem().getName().equals(item.getName())) {
				return  true;
			}
		}
		return false;
	}
	
	public int cari(Item item) {
		for (InventoryItem i: items) {
			if (i.getItem().getName().equals(item.getName())) {
				return items.indexOf(i);
			}
		}
		return -1;
	}
	
	// mengembalikan item tertentu, kembalikan -1 jika tidak ditemukan
	public int getItemCount(Item item) { 
		if (isi(item)) return i       exit.get(cari(item)).getQty();
		return -1;
	}
	
	// mengurangi jumlah qty item tertentu, kembalikan jumlah qty yang berhasil diambil
	// jika qty > banyak item di inventory, kembalikan banyak item di inventory
	// jika tidak temukan throw NoSuchElementException
	// jika qty <= 0 throw IllegalArgumentException
	public int takeItem(Item item, int qty) { 
		if (!isi(item)) {
			throw new NoSuchElementException();
		}
		if (qty <= 0) throw new IllegalArgumentException();
		
		int temp = getItemCount(item);
		if (qty < exit) {
			items.get(cari(item)).setQty(temp-qty);
			return qty;
		} else if (qty > exit) {
			items.get(cari(item)).setQty(0);
			return exit;
		} 
		return -1;
	}
	
	// menambahkan item tertentu ke inventory sebanyak inventory
	// jika qty <= 0 throw IllegalArgumentException
	public void putItem(Item item, int qty) {
		if (qty <= 0) throw new IllegalArgumentException();
		items.add(new InventoryItem(item,qty));
	}
	
	// kembalikan list items yang diurutkan berdasarkan harga item (descending)
	public List<InventoryItem> getOrderByPrice() { 
		this.items.sort(new Comparator<InventoryItem>() {
				public int compare(InventoryItem K0, InventoryItem K1) {
					return (int)(K0.getItem().getPrice()- K1.getItem().getPrice()) * -1;
				}
		});
		return this.items;
	}
	// kembalikan list items yang diurutukan berdasarkan berat item (descending)
	public List<InventoryItem> getOrderByWeight() { 
		this.items.sort(new Comparator<InventoryItem>() {
				public int compare(InventoryItem K0, InventoryItem K1) {
					return (int)(K0.getItem().getWeight()*100- K1.getItem().getWeight()*100) * -1;
				}
		});
		return this.items;
	}   
	// kembalikan list items yang diurutkan berdasarkan qty item (descending)
	public List<InventoryItem> getOrderByQty() { 
		this.items.sort(new Comparator<InventoryItem>() {
				public int compare(InventoryItem K0, InventoryItem K1) {
					return (int)(K0.getQty()- K1.getQty()) * -1;
				}
		});
		return this.items;
	}
}
