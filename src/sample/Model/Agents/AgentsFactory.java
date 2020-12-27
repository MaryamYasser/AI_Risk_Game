package sample.Model.Agents;

public class AgentsFactory {
    String agents[] = {"Passive", "Aggressive", "Pacifist", "A* player", "RTA-star", "Greedy player", "Minimax player"};

    public String[] GetAgents(){

        return agents;
    }

    public BasicAgent getAgent(String agent){
        if (agent.equalsIgnoreCase(agents[0]))
            return new PassiveAgent();
        if (agent.equalsIgnoreCase(agents[1]))
            return new AgressiveAgent();
        if (agent.equalsIgnoreCase(agents[2]))
            return new PacifistAgent();

        if (agent.equalsIgnoreCase(agents[3]))
            return new A_StarAgent();
        if (agent.equalsIgnoreCase(agents[4]))
            return new RTAstar();
        if (agent.equalsIgnoreCase(agents[5]))
            return new GreedyAgent();


        if(agent.equalsIgnoreCase("Human"))
            return new HumanAgent();




       // if (agent.equalsIgnoreCase(agents[6]))
           // return new GreedyAgent();
        return null;
    }
}
