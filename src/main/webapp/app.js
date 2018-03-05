import React from 'react';
import ReactDOM from 'react-dom';
import { combineReducers, applyMiddleware, createStore } from 'redux';
import { Provider } from 'react-redux';
import thunk from 'redux-thunk'
import { ConnectedRouter, routerReducer, routerMiddleware, push } from 'react-router-redux'
import createHistory from 'history/createBrowserHistory'

import getBuyList from './reducers/buyReducer';
import login from './reducers/loginReducer';
import getInventory from './reducers/sellReducer';
import getQuestsList from './reducers/questsReducer';
import user from './reducers/userReducer';
import getMySellItems from './reducers/mySellingReducer'

import MainPage from './components/MainPage'

import 'bootstrap/dist/css/bootstrap.css';
import '../resources/static/styles/main.css';
import './images/Logo.png';
import 'material-design-icons/iconfont/material-icons.css';
import './libs/jquery-cookie/jquery.cookie.js'


import SockJS from "sockjs-client"
let Stomp = require("stompjs/lib/stomp.js").Stomp;


const history = createHistory();
const middleware = routerMiddleware(history);
const reducer = combineReducers({getBuyList, getMySellItems, login, getInventory, getQuestsList, user, routing: routerReducer});
const store = createStore(reducer, applyMiddleware(thunk, middleware));



class MainPageWithRouter extends React.Component{
    render(){return(
        <ConnectedRouter history={history}>
            <MainPage />
        </ConnectedRouter>
    )}
}

export var stompClient = null;
let socket = new SockJS('/updateItems');
stompClient = Stomp.over(socket);

ReactDOM.render(
    <Provider store={store}>
        <MainPageWithRouter/>
    </Provider>, document.getElementById('root')
);