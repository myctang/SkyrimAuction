import React from 'react';
import ReactDOM from 'react-dom';
import AppBar from 'react-toolbox/lib/app_bar';
import Navigation from 'react-toolbox/lib/navigation';
import Button from 'react-toolbox/lib/Button';
import Link from 'react-toolbox/lib/Link';
import $ from 'jquery';
import 'bootstrap/dist/css/bootstrap.css';
import '../resources/static/styles/main.css';
import './images/Logo.png';
import 'material-design-icons/iconfont/material-icons.css';

class MainPage extends React.Component{
    render(){
        return (
        <AppBar title='Skyrim Auction'>
            <Navigation type='horizontal' className="navigate">
                <div className="buttons">
                    <Button label="Купить" raised primary/>
                    <Button label="Продать" raised primary/>
                    <Button label="Лоты" raised primary/>
                    <Button label="Обмен" raised primary/>
                    <Button label="Задания" raised primary/>
                </div>
                <div className="cabinet">
                    <Link label="Личный кабинет"/>
                </div>
            </Navigation>
        </AppBar>
    )
    }
}

ReactDOM.render(
    <MainPage />, document.getElementById('root')
);