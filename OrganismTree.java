// Angkan Baidya
// 112309655
// R31

/**
 *
 * The OrganismTree implements the ternary tree of OrganismNode objects.
 *
 */
public class OrganismTree {

    /**
     * Attribute root represents the root of the tree (the apex predator).
     */
    private OrganismNode root;
    /**
     * Attribute cursor represents the reference the node that the user wants to be
     * at.
     */
    private OrganismNode cursor;

    /**
     * Attribute path represents the path from the root to the current cursor.
     */
    private String path;
    /**
     * Attribute plants represents the list of the plants in the food pyramid.
     */
    private String plants;

    /**
     * Custom constructor for the OrganismTree class with apexPredator as the root.
     *
     */
    public OrganismTree(OrganismNode apexPredator) {
        this.root = apexPredator;
        this.cursor = apexPredator;
        path = apexPredator.getName();
    }


    /**
     * Moves the cursor back to the root of the tree.
     */
    public void cursorReset() {
        this.cursor = this.root;
        path = this.root.getName();
    }


    /**
     * Moves cursor to one of cursor’s children.
     * throws IllegalArgumentException thrown if name references an exact name with
     *                                  one of its would-be siblings.
     */
    public void moveCursor(String name) throws IllegalArgumentException {
        String position = this.cursor.getPosition(name);
        if (position == null) {
            throw new IllegalArgumentException("ERROR: This prey does not exist for this predator.");
        } else {
            if (position.equals(OrganismNode.LEFT_LABEL)) {
                this.cursor = this.cursor.getLeft();

            } else if (position.equals(OrganismNode.MIDDLE_LABEL)) {
                this.cursor = this.cursor.getMiddle();
            } else {
                this.cursor = this.cursor.getRight();
            }
            path += " -> " + this.cursor.getName();
        }
    }

    /**
     * Prints the organism at cursor and all its possible prey .
     *
     * returns String containing the name of the cursor, and all the cursor’s
     *         possible prey
     * throws IsPlantException thrown if the OrganismNode is a plant.
     */
    public String listPrey() throws IsPlantException {
        StringBuffer listPrey = new StringBuffer(this.cursor.getName());
        if (this.cursor.getLeft() != null) {
            listPrey.append(" -> ");
            listPrey.append(this.cursor.getLeft().getName());
        }
        if (this.cursor.getMiddle() != null) {
            listPrey.append(" , ");
            listPrey.append(this.cursor.getMiddle().getName());
        }
        if (this.cursor.getRight() != null) {
            listPrey.append(" , ");
            listPrey.append(this.cursor.getRight().getName());
        }

        return listPrey.toString();

    }

    /**
     * Returns a String containing the path of organisms that leads from the apex
     * predator (root) to the organism at cursor.
     *
     * returns String containing the food chain from the apex predator to the
     *         cursor.
     */
    public String listFoodChain() {
        return path;
    }


    /**
     * Prints out a layered, indented tree by performing a preorder traversal
     * starting at cursor.
     */
    public void printOrganismTree() {
        String indent = "";
        printNode(this.cursor, indent);
    }


    /**
     * Creates the indent in display of the food pyramid.
     *
     */
    private void printNode(OrganismNode on, String indent) {
        if (on != null) {
            System.out.println(indent + on);
            if (on.hasChilds()) {
                printNode(on.getLeft(), indent + "  ");
                printNode(on.getMiddle(), indent + "  ");
                printNode(on.getRight(), indent + "  ");
            }
            indent += "  ";
        }
    }


    /**
     * Displays the contents of the variable plants as required.
     *
     */
    private void printPlantNode(OrganismNode on) {
        if (on != null) {

            if (on.hasChilds()) {
                printPlantNode(on.getLeft());
                printPlantNode(on.getMiddle());
                printPlantNode(on.getRight());
            } else {
                if (on.getIsPlant()) {
                    this.plants += (this.plants.equals("") ? "" : ", ") + on.getName();
                }
            }
        }
    }

    /**
     * Returns a list of all plants currently at cursor and beneath cursor in the
     * food pyramid.
     *
     */
    public String listAllPlants() {
        this.plants = "";
        printPlantNode(this.root);
        return this.plants;

    }


    /**
     * Creates a new animal node with a specific name and diet and adds it as a
     * child of the cursor node.

     */
    public void addAnimalChild(String name, boolean isHerbivore, boolean isCarnivore)
            throws IllegalArgumentException, PositionNotAvailableException, IsPlantException, DietMismatchException {
        OrganismNode prey = new OrganismNode(name, isHerbivore, isCarnivore);
        if (cursor.childNameExist(name)) {
            throw new IllegalArgumentException("ERROR: This prey already exists for this predator.");

        } else
            this.cursor.addPrey(prey);
    }

    /**
     * Creates a new plant node with a specific name and adds it as a child of the
     * cursor node.
     * throws IllegalArgumentException      thrown if name references an exact name
     *                                       with one of its would-be siblings.
     * throws PositionNotAvailableException thrown if there is no available child
     *                                       position for a new node to be added.
     * throws IsPlantException              thrown if the OrganismNode is a plant.
     * throws DietMismatchException         thrown if preyNode does not correctly
     *                                       correspond to the diet of this animal.
     */
    public void addPlantChild(String name)
            throws IllegalArgumentException, PositionNotAvailableException, IsPlantException, DietMismatchException {
        OrganismNode prey = new OrganismNode(name);
        if (cursor.childNameExist(name)) {
            throw new IllegalArgumentException("ERROR: This prey already exists for this predator.");

        } else
            this.cursor.addPrey(prey);

    }

    /**
     * Removes the child node of cursor with the indicated name.
     * throws IllegalArgumentException thrown if name does not reference an exact
     *                                  name of the children of the node.
     */
    public void removeChild(String name) throws IllegalArgumentException {
        if (!this.cursor.childNameExist(name)) {
            throw new IllegalArgumentException("ERROR: This name does not reference a direct child of the cursor");

        } else {
            this.cursor.shiftPositions(name);
        }

    }

    /**
     * Gets the name of the cursor node.
     */
    public String getCursorName() {
        return this.cursor.getName();
    }

    /**
     * Gets the cursor node.
     */
    public OrganismNode getCursor() {
        return cursor;
    }
}
