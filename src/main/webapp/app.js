import React from 'react';
import ReactDOM from 'react-dom';
import { combineReducers } from 'redux';
import { Provider } from 'react-redux';
import { createStore } from 'redux'
import $ from 'jquery';

import AppBar from 'react-toolbox/lib/app_bar';
import Navigation from 'react-toolbox/lib/navigation';
import Button from 'react-toolbox/lib/Button';
import Link from 'react-toolbox/lib/Link';

import * as reducers from './reducers/any';

import 'bootstrap/dist/css/bootstrap.css';
import '../resources/static/styles/main.css';
import './images/Logo.png';
import 'material-design-icons/iconfont/material-icons.css';



const reducer = combineReducers(reducers);
const store = createStore(reducer, []);


class MainPage extends React.Component{
    render(){
        return (
            <Provider store={store}>
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
             </Provider>
    )
    }
}

ReactDOM.render(
    <MainPage />, document.getElementById('root')
);