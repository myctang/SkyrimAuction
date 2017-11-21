import React from 'react';
import ReactDOM from 'react-dom';
import $ from 'jquery';
import 'bootstrap/dist/css/bootstrap.css';
import './styles/main.css';
import './images/Logo.png';

class MainPage extends React.Component{
    render(){
        return(
            <div className="header container-fluid">
                <div className="row">
                    <div className="logo col-sm-3">
                        <img src="./images/Logo.png" alt="Logo"/>
                    </div>
                    <div className="siteName col-sm-6">
                        Аукцион
                        <b>Скайримиум</b>
                    </div>
                    <div className="languages col-sm-1">
                        Русский
                    </div>
                </div>
            </div>
        )
    }
}

ReactDOM.render(
    <MainPage />, document.getElementById('root')
);