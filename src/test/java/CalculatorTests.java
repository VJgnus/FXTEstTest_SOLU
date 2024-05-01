import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import model.CalcModel;
import model.Operation;

class CalculatorTests
{

	CalcModel model;
	
	DoubleProperty num1 = new SimpleDoubleProperty();
	DoubleProperty num2 = new SimpleDoubleProperty();
	DoubleProperty result = new SimpleDoubleProperty();
	@BeforeEach
	void setUp() throws Exception
	{
		model = new CalcModel();
		num1.set(10);
		num2.set(5);;
	}

	@Test
	void test()
	{
		testSetNums();
		testAdd();

	}

	public void testSetNums()
	{
		model.setNum1(num1);
		model.setNum2(num2);
		assertEquals(model.getNum1().get(), 10);
		assertEquals(model.getNum2().get(), 5);
	}
	public void testAdd()
	{
		model.add();
		assertEquals(model.getResult().get(), 15);
	}
}
