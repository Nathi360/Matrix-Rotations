import java.util.*;

/*
Note: A matrix rotation can be simulated by two operations --- (1) Transposing the Matrix
                                                               (2) Reversing the rows of the Matrix

State: the actual building of HMatrix is incomplete.
       the transpose() and reverseRows() seems to have a minor problem. (See output)
 */

public class Hackonacci
{
    private String[][] HMatrix;
    private String[][] modMatrix;
    private final int size;
    private Queue <Integer> differences;

    public Hackonacci(int big, Queue <Integer> queries)
    {
        System.out.println("Constr");

        size = big;
        HMatrix = new String[size][size];
        modMatrix = new String[size][size];
        differences = new ArrayDeque<>();

        buildMatrix();

        while(!queries.isEmpty())
        {
            rotateMatrix(queries.remove());
        }
    }

    //assignCharacter(((R + 1)*(C + 1))*((R + 1)*(C + 1)))

    private void buildMatrix()
    {
        System.out.println("Build");
        for (int R = 0; R < size; R++)
        {
            for (int C = 0; C < size; C++)
            {
                if(R == 0 && C == 0)
                {
                    HMatrix[R][C] = "Y";
                }
                else if(R == 1 && C == 2)
                {
                    HMatrix[R][C] = "Y";
                }
                else if(R == 1 && C == 3)
                {
                    HMatrix[R][C] = "Y";
                }
                else if(R == 2 && C == 1)
                {
                    HMatrix[R][C] = "Y";
                }
                else if(R == 3 && C == 1)
                {
                    HMatrix[R][C] = "Y";
                }
                else
                {
                    HMatrix[R][C] = "X";
                }
            }
        }

        System.out.println("*******");
        PrintMatrix();
    }

    private String assignCharacter(int input)
    {
        return "underConstruction!";
    }

    private void rotateMatrix(int angle)
    {
        System.out.println("Rotate");
        modMatrix = HMatrix;
        int numRotations = angle/90;    int track = 0;
        System.out.println("Rotating " + numRotations + " times.");

        while(track < numRotations)
        {
            transpose();
            track++;
        }
    }

    private void transpose()
    {
        System.out.println("TransposeRR");
        Queue <String> veck = new ArrayDeque<>();    int C;

        for (int R = 0; R < size; R++)
        {
            for (C = 0; C < size; C++)
            {
                veck.add(HMatrix[R][C]);
            }

            populateModMatrix(veck, (C - 1));
        }

        reverseRows();
    }

    private void populateModMatrix(Queue <String> M, int coll)
    {
        System.out.println("PopulateMonM");
        for (int R = 0, C = coll; R < size && !M.isEmpty(); R++)
        {
            modMatrix[R][C] = M.remove();
        }
    }

    private void reverseRows()
    {
        System.out.println("ReverseRows");
        Stack <String> bound = new Stack<>();

        for (int R = 0; R < size; R++)
        {
            for (int C = 0; C < size; C++)
            {
                bound.push(modMatrix[R][C]);
            }

            assignReversed(bound, R);
        }
    }

    private void assignReversed(Stack <String> B, int row)
    {
        System.out.println("assignReversed");
        for (int C = 0, R = row; C < size && !B.isEmpty(); C++)
        {
            modMatrix[R][C] = B.pop();
        }
    }

    private int countDifferences()
    {
        int count = 0;

        for (int R = 0; R < size; R++)
        {
            for (int C = 0; C < size; C++)
            {
                if(HMatrix[R][C] != modMatrix[R][C])
                {
                    count++;
                }
            }
        }

        return count;
    }

    private void PrintMatrix()
    {
        System.out.println("Print");
        for (int R = 0; R < size; R++)
        {
            for (int C = 0; C < size; C++)
            {
                System.out.print(HMatrix[R][C] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        Scanner dash = new Scanner(System.in);

        int n = dash.nextInt();
        int q = dash.nextInt();

        int track = 0;
        Queue <Integer> queries = new ArrayDeque<>(q);

        while(track < q)
        {
            queries.add(dash.nextInt());
            track++;
        }

        Hackonacci dicci = new Hackonacci(n, queries);
        dicci.PrintMatrix();
    }
}
