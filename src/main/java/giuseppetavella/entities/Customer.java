package giuseppetavella.entities;

import giuseppetavella.enums.CustomerTier;

public class Customer {
    private final long id;
    private final String name;
    private CustomerTier tier;

    public Customer(long id, String name, CustomerTier tier) {
        this.id = id;
        this.name = name;
        this.setTier(tier);
    }

    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    /**
     * returns something like:
     *    Giuseppe (ID:5)
     * Useful as key in grouping by operations.
     */
    public String getUniqueLabel() {
        return this.getName() + " (ID:" + this.getId()+")";
    }


    public CustomerTier getTier() {
        return tier;
    }

    public void setTier(CustomerTier tier) {
        this.tier = tier;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tier=" + tier +
                '}';
    }
}
