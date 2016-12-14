package astrolabe31.runningfx.util;
import java.util.ArrayList;

/**
 * Permet de construire des tableaux dynamiques � 2 dimensions
 * @author Tapmoa
 *
 * @param <Type>
 */
public class array2D<Type>
{
    ArrayList<ArrayList<Type>>  array;
 
    public array2D()
    {
        array = new ArrayList<ArrayList<Type>>();
    }
    /**
     * Ajoute une ligne � la fin du tableau.
     */
    public void addRow()
    {
        array.add(new ArrayList<Type>());
    }
    /**
     * Ajoute une ligne � la position indiqu�e.
     * @param row
     */
    public void addRow(int row)
    {
        if (row >= 0 && row < this.getNbRows())
            array.add(row, new ArrayList<Type>());
    }
    /**
     *  Ajoute une colone � la fin du tableau � la ligne x. Cette colone est initialis� � null.
     */
    public void addCol(int x)
    {
        if (x >= 0 && x < this.getNbRows())
            array.get(x).add(null);
    }
    /**
     * Ajoute une colone � la position y � la ligne x. Cette ligne est initialis� � null.
     * @param x
     * @param y
     */
    public void addCol(int x, int y)
    {
        array.get(x).add(y, null);
    }
    /**
     * Ajoute un �l�ment � la ligne x et � la colonne y.
     * @param x
     * @param y
     * @param T
     */
    public void addCol(int x, int y, Type T)
    {
            array.get(x).add(y, T);
    }
    /**
     * Recup�re l'objet situ� � la ligne row et � la colone col.
     * @param row
     * @param col
     * @return
     */
    public Type get(int row, int col)
    {
        return array.get(row).get(col);
    }
    /**
     * Change l'objet situ� � la ligne row et � la colone col.
     * @param row
     * @param col
     * @param data
     */
    public void set(int row, int col, Type data)
    {
        if (col >= 0 && col < this.getNbCols())
            array.get(row).set(col,data);
    }
    /**
     * Retourne le nombre de ligne du tableau.
     * @return
     */
    public int getNbRows()
    {
        return array.size();
    }
    /**
     * Retourne le nombre de colone du tableau.
     * @return
     */
    public int getNbCols()
    {
        return array.get(0).size();
    }
}

