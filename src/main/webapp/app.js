import React from 'react';
import ReactDOM from 'react-dom';
import AppBar from 'react-toolbox/lib/app_bar';
import Navigation from 'react-toolbox/lib/navigation';
import Button from 'react-toolbox/lib/Button';
import Link from 'react-toolbox/lib/Link';
import $ from 'jquery';
import 'bootstrap/dist/css/bootstrap.css';
import './styles/main.css';
import './images/Logo.png';
import 'material-design-icons/iconfont/material-icons.css';

class MainPage extends React.Component{
    render(){
        return (
        <AppBar title='Skyrim Auction'>
            <Navigation type='horizontal'>
                <Button label="Купить"/>
                <Button label="Продать"/>
                <Button label="Лоты"/>
                <Button label="Обмен"/>
                <Button label="Задания"/>
                <Link label="Личный кабинет"/>
            </Navigation>
        </AppBar>
    )
    }
}

ReactDOM.render(
    <MainPage />, document.getElementById('root')
);