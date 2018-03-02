import React from 'react';
import Buy from "./Buy";
import Sell from "./Sell";
import Quest from './Quests';
import MySellItems from './MySellItems';
import Me from './Me';
import {Switch, Route} from 'react-router-dom';

export class MainRouting extends React.Component{
    render(){
        console.log("mainRouting render");
        debugger;
        return(
            <Switch>
                <Route exact path="/" component={Buy}/>
                <Route path="/sell" component={Sell}/>
                <Route path="/quests" component={Quest}/>
                <Route path="/mysells" component={MySellItems}/>
                <Route path="/me" component={Me}/>
            </Switch>
        )
    }
}