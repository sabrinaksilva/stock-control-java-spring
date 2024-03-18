# Stock and Sales control system

## Skills and technologies applied

- [ ]  Complete CRUD with Spring web, Spring security, JPA, Tests

### System features

- [ ] Create, Read, Update and delete simple stock products (not composed by N others products)
- [ ] Create, Read, Update and delete composed products: those which are composed by multiple other products in stock
    - The components products in this composition are used to calculate cost value
    - Add or remove composed products propagates it to its components - like changing their current quantity in stock
    - The availability to sell a composed product depends on the availability of all of its components and their
      respective
      quantities necessary in that composition
    - 