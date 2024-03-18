# Stock Control System

## IMPORTANT: 
Even though some endpoints are not implemented as of 4 a.m. on 18/03/2024, the main focus was to showcase my code style, architecture, clean code, and extensive testing (unit tests and database integration tests). Other features are yet to be implemented.

## Context:
Some client (Let's call him Cris) deals with the acquisition and manufacturing of diverse kinds of products and sells those products, requiring stock control for them:

- A product can be composed of other products from its inventory or can be a component sold separately. 
- One of the requirements is: if a product A uses (x amounts of product B + y amounts of product C), when product A is constructed, those x and y amounts must be subtracted from products B and C, respectively, but the current quantity of an entire produced computer will increase.

## Use Cases:
- The client is able to sell computer processors, motherboards, air coolers for computers, and all the related components of a computer separately.
- Each computer component mentioned above is a product in their stock, which registers the current quantity of this product in the inventory, along with information such as name, and others.
- The client is also able to sell an entire desktop computer built from the items (computer components mentioned above) he owns in his inventory.
- The client can already have x amounts of desktop computers produced by him and ready to sell it with immediate delivery. The stock control will show that x desktop computers can be sold immediately - no need to manufacture them.

If the client does not have any desktop computers already produced and ready to be sold, they can check if they have all the computer components needed to manufacture a new one and sell it.

- The stock will provide information about how many computers can be produced with the current components available in their inventory.
- The system registers what components are needed to produce the computer, as well as how much of each component is needed to produce it.
- For example: to produce a computer, in addition to other components, you need 2 air coolers, 1 processor, and 1 motherboard.

The client is now able to know if/how many computers can be produced depending on the quantity of each necessary supply they have in their stock.
- Example: if they only needed 2 air coolers, 1 processor, and 1 motherboard to build up a computer and sell it, and currently have 3 processors, 5 air coolers, and 6 motherboards (remembering: each one of them can be sold separately!), the system must provide information that only 2 computers can be produced with such an amount of supplies.

If 1 computer is constructed, it will be registered as a product in the database.
- Should increment the current available quantity in stock of the computer.
- Should decrement the used components to manufacture the computer.

Even the computer - a product which needs certain amounts of different components - could be a component of another product, like one which comes along with a keyboard and LEDs.

# Running and checking the project
- install maven and some IDE like Intellij Comunnit
- create  a local database and register its name and your credentials - by variables, if sensible credentials.
- run Maven reload in pom.xml to download all dependencies
- Activate the google code style
- Make sure Java 21 is installed and configured in the project setup at the IDE
- Verify the code quality and sctructure - I am all open to new improvements sugestions
- Test the implemented funcionalities by reading and running the automatizated tests
