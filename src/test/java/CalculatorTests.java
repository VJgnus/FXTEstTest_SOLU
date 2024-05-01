import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.CalcModel;


class CalculatorTests
{

	CalcModel model;
	
	
	@BeforeEach
	void setUp() throws Exception
	{
		model = new CalcModel();
		
	}

	@Test
		void add() {
	        setNums(8, 3);
	        model.add();
	        assertEquals(11, getResultVal());
	    }

	private Double getResultVal()
	{
		return model.getResult().getValue();
	}

	private void setNums(int num1, int num2) {
        model.getNum1().setValue(num1);
        model.getNum2().setValue(num2);
    }

}
