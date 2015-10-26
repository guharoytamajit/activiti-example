package myActiviti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

public class Main {
	public static void main(String[] args) {
		ProcessEngine processEngine = ProcessEngineConfiguration
				.createStandaloneProcessEngineConfiguration()
				.setJdbcDriver("com.mysql.jdbc.Driver")
				.setJdbcUrl("jdbc:mysql://localhost:3306/activiti")
				.setJdbcPassword("admin").setJdbcUsername("root")
				.setMailServerHost("smtp.gmail.com").setMailServerPort(587)
				.setMailServerUsername("guharoytamajit@gmail.com")
				.setMailServerPassword("").setMailServerUseTLS(true)
				.buildProcessEngine();

		RepositoryService repositoryService = processEngine
				.getRepositoryService();

		/*
		 * repositoryService .createDeployment()
		 * .addInputStream("test.bpmn20.xml",
		 * ReflectUtil.getResourceAsStream("diagrams/test.bpmn")) .deploy();
		 */

		// repositoryService.suspendProcessDefinitionByKey("test1");
		// repositoryService.activateProcessDefinitionByKey("test1");

		/*
		 * RuntimeService runtimeService = processEngine.getRuntimeService();
		 * Map<String, Object> variableMap = new HashMap<String, Object>();
		 * variableMap.put("name", "Activiti"); ProcessInstance processInstance
		 * = runtimeService .startProcessInstanceByKey("test1", variableMap);
		 * assertNotNull(processInstance.getId()); System.out.println("id " +
		 * processInstance.getId() + " " +
		 * processInstance.getProcessDefinitionId());
		 */

		TaskService taskService = processEngine.getTaskService();
		List<Task> tasks = taskService.createTaskQuery().taskAssignee("kermit")
				.list();
		for (Task task : tasks) {
			System.out.println("Task available : " + task.getName());
		}

		Task task = tasks.get(0);
		Map<String, Object> taskVariables = new HashMap<String, Object>();
		taskVariables.put("age", "22");
		taskVariables.put("acceptOffer", "Accept");
		try {
			taskService.complete(task.getId(), taskVariables);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
