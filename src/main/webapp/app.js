import React from 'react';
import ReactDOM from 'react-dom';
import { combineReducers, applyMiddleware } from 'redux';
import { Provider } from 'react-redux';
import { createStore } from 'redux'
import thunk from 'redux-thunk'
import $ from 'jquery';

import AppBar from 'react-toolbox/lib/app_bar';
import Navigation from 'react-toolbox/lib/navigation';
import Button from 'react-toolbox/lib/Button';
import Link from 'react-toolbox/lib/Link';
import Buy from './components/Buy'

import * as buyReducers from './reducers/buyReducer';

import 'bootstrap/dist/css/bootstrap.css';
import '../resources/static/styles/main.css';
import './images/Logo.png';
import 'material-design-icons/iconfont/material-icons.css';



const reducer = combineReducers(buyReducers);
debugger;
const store = createStore(reducer, applyMiddleware(thunk));


class MainPage extends React.Component{
    render(){
        return (
            <div>
                <AppBar title='Skyrim Auction'>
                    <Navigation type='horizontal' className="navigate">
                        <div className="buttonsMenu">
                            <div className="buttons">
                                <Button label="Купить" raised primary/>
                                <Button label="Продать" raised primary/>
                                <Button label="Лоты" raised primary/>
                                <Button label="Обмен" raised primary/>
                                <Button label="Задания" raised primary/>
                            </div>
                        </div>
                        <div className="cabinet">
                            <Link label="Личный кабинет"/>
                        </div>
                    </Navigation>
                </AppBar>
                <Buy />
            </div>
        )
    }
}

ReactDOM.render(
    <Provider store={store}>
        <MainPage />
    </Provider>, document.getElementById('root')
);