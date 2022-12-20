package com.xigaiou.xigaiouproject.common.gql;

import com.xigaiou.xigaiouproject.common.gql.Action;
import com.xigaiou.xigaiouproject.common.gql.ActionReq;
import com.xigaiou.xigaiouproject.common.gql.ActionResult;
import com.xigaiou.xigaiouproject.common.gql.Engine;
import com.xigaiou.xigaiouproject.common.gql.EngineTypeEnum;
import com.xigaiou.xigaiouproject.common.gql.FetchReq;
import com.xigaiou.xigaiouproject.common.gql.FetchResult;
import com.xigaiou.xigaiouproject.common.gql.Fetcher;
import com.xigaiou.xigaiouproject.common.gql.GeneralEngine;
import com.xigaiou.xigaiouproject.common.gql.GqlService;
import com.xigaiou.xigaiouproject.common.gql.GraphFetchReq;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class DefaultGqlService implements GqlService, ApplicationContextAware {
    private Map<String, Fetcher> fetcherMap;
    private Map<String, Action> actionMap;
    private Map<EngineTypeEnum, Engine> engineMap;

    public DefaultGqlService(@Autowired(required = false) Action[] actions,
                             @Autowired(required = false) Collection<Action>[] actionProviders,
                             @Autowired(required = false) Fetcher[] fetchers){
        actionMap = new HashMap<>();
        fetcherMap = new HashMap<>();
        engineMap = new HashMap<>();
        if(null != actions){
            addActions(actions);
        }

        if(null != fetchers){
            int fetcherLength = fetchers.length;
            for(int i = 0 ;i< fetcherLength; ++i){
                addFetchers(fetchers);
            }
        }
    }

    public void addFetchers(Fetcher[] fetchers){
        /*
        addFetchers((Iterable)Arrays.asList(fetchers));
        */
        for (Fetcher fetcher : fetchers) {
            addAction(fetcher);
        }
    }

    public void addFetchers(Iterable<Fetcher> fetchers){
        Iterator iterator = fetchers.iterator();
        while(iterator.hasNext()){
            Fetcher fetcher = (Fetcher) iterator.next();
            addAction(fetcher);;
        }
    }

    public void addActions(Action[] actions){
        /*
        addFetchers((Iterable)Arrays.asList(actions));
        */
        for (Action action : actions) {
            addAction(action);
        }
    }

    public void addActions(Iterable<Action> actions){
        Iterator iterator = actions.iterator();

        while(iterator.hasNext()){
            Action action = (Action)iterator.next();
            addAction(action);
        }
    }

    public void addAction(Action action){
        actionMap.put(action.getDataId(), (Fetcher)action);
        //已修改
        /*
        if(action  instanceof ActionParent){
            addActions((Iterable) ((ActionParent)action));
        }
        */
    }

    public <T> Fetcher<T> getFetcher(String name, boolean required) {
        Fetcher fetcher = (Fetcher) fetcherMap.get(name);
        if(null == fetcher && required){
            throw new RuntimeException("not found the fetcher");
        }
        return fetcher;
    }

    public <T> Action<T> getAction(String name, boolean required) {
        Action action = (Action) actionMap.get(name);
        if(null == name && required){
            throw new RuntimeException("not found the fetcher");
        }
        return action;
    }

    public Fetcher[] getFetchers() {
        return (Fetcher[])fetcherMap.values().toArray(new Fetcher[0]);
    }

    public FetchResult executeFetch(FetchReq req){
        return getEngine(req.getEngineType()).fetch(req);
    }

    public Engine getEngine(EngineTypeEnum engineType){
        Engine engine = (Engine)engineMap.get(engineType);
        if(null == engine){
            System.out.println("error:engine is null");
            throw new RuntimeException("error:engine is null");
        }
        return engine;
    }

    public ActionResult executeAction(ActionReq req){
        return getEngine(req.getEngineType()).execute(req);
    }

    public FetchResult executeGql(String gql){
        return executeGql(gql, new HashMap<>());
    }

    public FetchResult executeGql(String gql, Map<String, Object> map){
        return executeFetch(new GraphFetchReq(gql, map));
    }

    public void setApplicationContext(ApplicationContext context) throws BeansException{
        try{
            this.engineMap.put(EngineTypeEnum.GENERAL, new GeneralEngine(this));
            //---------
            Iterator iterator = actionMap.values().iterator();

            while(iterator.hasNext()) {
                Action action = (Action) iterator.next();
                action.afterInit();
            }
        } catch (Throwable throwable){
            throw throwable;
        }
    }
}
