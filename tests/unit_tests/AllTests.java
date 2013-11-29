package unit_tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ Test_CSVHandler.class, Test_GameStatus.class, Test_Grid.class,
		Test_GridFileLoader.class, Test_PairRecord.class,
		Test_PlayerRecord.class, Test_Racer.class, Test_Statistics.class,
		Test_UserManagement.class })
public class AllTests {

}
