// Angkan Baidya
// 112309655
// R31



/**
 *
 * The OrganismNode represents a single node/species in the tree.
 *
 */
public class OrganismNode {
    /**
     * Attribute name represents the name of the organismNode’s species.
     */
    private String name;
    /**
     * Attribute isPlant indicates that the node is a plant.
     */
    private boolean isPlant;
    /**
     * Attribute isHerbivore indicates that the node is herbivore.
     */
    private boolean isHerbivore;
    /**
     * Attribute isCarnivore indicates that the node is carnivore.
     */
    private boolean isCarnivore;
    /**
     * Attribute left represents a reference for the left child of the organismNode.
     */
    private OrganismNode left;
    /**
     * Attribute middle represents a reference for the middle child of the
     * organismNode.
     */
    private OrganismNode middle;
    /**
     * Attribute right represents a reference for the right child of the
     * organismNode.
     */
    private OrganismNode right;

    /**
     *  RIGHT_LABEL indicates that the organismNode is a left child to the
     * organismNode parent.
     */
    public static final String RIGHT_LABEL = "right";
    /**
     *  MIDDLE_LABEL indicates that the organismNode is a middle child to
     * the organismNode parent.
     */
    public static final String MIDDLE_LABEL = "middle";
    /**
     * t LEFT_LABEL indicates that the organismNode is a right child to the
     * organismNode parent.
     */
    public static final String LEFT_LABEL = "left";

    /**
     * Default constructor for the organismNode class.
     */
    public OrganismNode() {

    }

    /**
     * Custom constructor for the organismNode class.
     *
     */
    public OrganismNode(String name, boolean isHerbivore, boolean isCarnivore) {
        this.name = name;
        this.isCarnivore = isCarnivore;
        this.isHerbivore = isHerbivore;
        this.isPlant = false;
    }

    /**
     * Custom constructor for the organismNode class.
     *
     */
    public OrganismNode(String name, String diet) {
        this.name = name;
        this.isCarnivore = diet.equals("C") || diet.equals("O");
        this.isHerbivore = diet.equals("H") || diet.equals("O");
        this.isPlant = false;
    }

    /**
     * Custom constructor for the organismNode class if the OrganismNode is a plant.
     *
     */
    public OrganismNode(String name) {
        this.name = name;
        this.isPlant = true;
    }

    /**
     * Getter for the attribute name of the OrganismNode.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the attribute isPlant of the OrganismNode. returns plan
     */
    public boolean getIsPlant() {
        return isPlant;
    }

    /**
     * Getter for the attribute isHerbivore of the OrganismNode. Returns herbivore
     *
     */
    public boolean getIsHerbivore() {
        return isHerbivore;
    }

    /**
     * Getter for the attribute isCarnivore of the OrganismNode.
     *
     */
    public boolean getIsCarnivore() {
        return isCarnivore;
    }

    /**
     * Getter for the attribute left child of the OrganismNode.
     */
    public OrganismNode getLeft() {
        return left;
    }

    /**
     * Getter for the attribute middle child of the OrganismNode.

     */
    public OrganismNode getMiddle() {
        return middle;
    }

    /**
     * Getter for the attribute right child of the OrganismNode.
     *
     */
    public OrganismNode getRight() {
        return right;
    }

    /**
     * Setter for the attribute name of the OrganismNode.
     *
     *
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for the attribute isPlant of the OrganismNode.
     *
     *
     */
    public void setIsPlant(boolean isPlant) {
        this.isPlant = isPlant;
    }

    /**
     * Setter for the attribute isHerbivore of the OrganismNode.
     *
     *
     */
    public void setIsHerbivore(boolean isHerbivore) {
        this.isHerbivore = isHerbivore;
    }

    /**
     * Setter for the attribute isCarnivore of the OrganismNode.
     *
     *
     */
    public void setIsCarnivore(boolean isCarnivore) {
        this.isCarnivore = isCarnivore;
    }

    /**
     * Adds preyNode as prey to the OrganismNode.
     * throws PositionNotAvailableException thrown if there is no available child
     *                                       position for a new node to be added.
     * throws IsPlantException              thrown if the OrganismNode is a plant.
     * throws DietMismatchException         thrown if preyNode does not correctly
     *                                       correspond to the diet of this animal.
     */
    public void addPrey(OrganismNode preyNode)
            throws PositionNotAvailableException, IsPlantException, DietMismatchException {

        String position = null;

        if (this.getIsPlant()) {
            throw new IsPlantException("ERROR: The cursor is at a plant node. Plants cannot be predators.");

        } else {

            position = this.getAvailablePosition();
            if (position == null) {
                throw new PositionNotAvailableException(
                        "ERROR: There is no more room for more prey for this predator.");
            } else

                if (((this.isCarnivore) && (!preyNode.isPlant)) || ((this.isHerbivore) && (preyNode.isPlant))) {
                    if (position == LEFT_LABEL) {
                        this.left = preyNode;
                    } else if (position == MIDDLE_LABEL) {
                        this.middle = preyNode;
                    } else
                        this.right = preyNode;
                } else {
                    throw new DietMismatchException(
                            "ERROR: This prey cannot be added as it does not match the diet of the predator.");
                }

        }

    }


    /**
     * Gets the available child position for an OragnismNode where we can add the
     * prey.
     */
    public String getAvailablePosition() {

        String position = null;
        if (this.getLeft() == null) {
            position = LEFT_LABEL;
        } else {
            if (this.getMiddle() == null) {
                position = MIDDLE_LABEL;

            } else if (this.getRight() == null) {
                position = RIGHT_LABEL;

            }
        }
        return position;
    }

    /**
     * Gets the position of a specific prey of the parent OrganismNode.
     */
    public String getPosition(String name) {
        String position = null;
        if (this.left != null && this.left.getName().equals(name)) {
            position = LEFT_LABEL;
        } else {
            if (this.middle != null && this.middle.getName().equals(name)) {
                position = MIDDLE_LABEL;

            } else if (this.right != null && this.right.getName().equals(name)) {
                position = RIGHT_LABEL;

            }
        }
        return position;
    }


    /**
     * Checks if an organismNode has a prey with this same name.
     *
     */
    public boolean childNameExist(String newName) {
        boolean nameExists = false;
        if (this.left != null && (this.left.getName().equals(newName))) {
            nameExists = true;
        } else {
            if (this.middle != null && (this.middle.getName().equals(newName))) {
                nameExists = true;

            } else if (this.right != null && (this.right.getName().equals(newName))) {
                nameExists = true;

            }
        }

        return nameExists;

    }

    /**
     * Shifts positions of the preys of an OrganismNode when we remove one of the
     * children preys to respect the rule of insertion order:left then middle then
     * right.
     */
    public void shiftPositions(String name) {
        if ((this.left != null) && this.left.getName().equals(name)) {
            this.left = this.getMiddle();
            this.middle = this.getRight();
        } else if ((this.middle != null) && this.middle.getName().equals(name)) {
            this.middle = this.getRight();
        }
        this.right = null;
    }

    /**
     * Checks if the OrganismNode has a prey as a child or no.
     *
     */
    public boolean hasChilds() {
        if (this.left != null || this.middle != null || this.right != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    /**
     * Represents the symbols for the display of the preys in the food
     * pyramid for both plants and animals.
     */
    public String toString() {
        return ((!this.isPlant) ? "|- " : "- ") + this.getName();
    }

}
