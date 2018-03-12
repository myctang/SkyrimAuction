import React from 'react';
import $ from "jquery";
import * as userActions from "../actions/userActions";
import {connect} from "react-redux";
import {withRouter} from "react-router-dom";
import {bindActionCreators} from "redux";

import Input from 'react-toolbox/lib/input';
import Dialog from 'react-toolbox/lib/dialog';
import Button from 'react-toolbox/lib/Button';

export class Login extends React.Component{
    state = {
        activeRegistration: false,
        activeLogin: false,
        login: '',
        password: '',
        loginReg: '',
        passwordReg: ''
    };

    reverseRegistration = () => {
        this.setState({...this.state, activeRegistration: !this.state.activeRegistration})
    };

    reverseLogin = () => {
        this.setState({...this.state, activeLogin: !this.state.activeLogin})
    };

    handleChange = (name, value) => {
        this.setState({...this.state, [name]: value});
    };

    register = () => {
        $(".error").hide();
        if (this.state.loginReg === '' || this.state.passwordReg === ''){
            $("#not_input").show();
            return;
        }
        let User = {"name":this.state.loginReg, "password":this.state.passwordReg};
        $.ajax({
            context: this,
            url: "/api/registerUser",
            method: "POST",
            contentType: "application/json; charset=utf-8",
            data:JSON.stringify(User),
            statusCode:{
                201: function (xhr) {
                    debugger;
                    this.setState({...this.state, activeRegistration: false});
                },
                500: function () {
                    $("#duplicate").show()
                }
            }
        })
    };

    login = () => {
        $.ajax({
            context: this,
            url: "/oauth/token",
            method: "POST",
            data: {
                "grant_type":"password",
                "username":this.state.login,
                "password":this.state.password
            },
            beforeSend: function (xhr) {
                xhr.setRequestHeader ("Authorization", "Basic " + btoa("trusted-app:secret"));
            },
            statusCode:{
                400: function (xhr) {
                    $("#errorLogin").show();
                },
                200: function (xhr) {
                    let expires_time = new Date();
                    expires_time.setTime(expires_time.getTime() + (parseInt(xhr["expires_in"]) * 1000));
                    $.cookie("access_token", xhr["access_token"], {expires: expires_time});
                    this.setState({...this.state, activeLogin: false});
                    console.log("login");
                    this.props.loginSuccess.loginSuccess($.cookie("access_token"));
                    this.props.userActions.getUserInfo($.cookie("access_token"));
                }
            }
        });
    };

    registrationActions = [
        { label: "Зарегистрироваться", onClick: this.register }
    ];

    loginActions = [
        { label: "Войти", onClick: this.login }
    ];

    render() {return (
        <div>
            <Button label="Войти" raised primary style={{marginRight: '10px'}} onClick={this.reverseLogin}/>
            <Dialog
                actions={this.loginActions}
                active={this.state.activeLogin}
                onEscKeyDown={this.reverseLogin}
                onOverlayClick={this.reverseLogin}
                title='Войти'
            >
                <Input type='text' label='Логин' name='login' value={this.state.login}
                       onChange={this.handleChange.bind(this, 'login')} required/>
                <Input type='password' label='Пароль' name='password' value={this.state.password}
                       onChange={this.handleChange.bind(this, 'password')} required/>
                <p id="errorLogin" className="error">Неверный логин или пароль</p>
            </Dialog>
            <Button label="Зарегистрироваться" raised primary onClick={this.reverseRegistration}/>
            <Dialog
                actions={this.registrationActions}
                active={this.state.activeRegistration}
                onEscKeyDown={this.reverseRegistration}
                onOverlayClick={this.reverseRegistration}
                title='Зарегистрироваться'
            >
                <Input type='text' label='Логин' name='loginReg' value={this.state.loginReg}
                       onChange={this.handleChange.bind(this, 'loginReg')} required/>
                <p id="duplicate" className="error">Такой логин уже занят</p>
                <Input type='password' label='Пароль' name='passwordReg' value={this.state.passwordReg}
                       onChange={this.handleChange.bind(this, 'passwordReg')} required/>
                <p id="not_input" className="error">Введите логин и пароль</p>
            </Dialog>
        </div>
    )}

}

function mapStateToProps(state) {
    console.log("Login->mapStateToProps");
}

function mapDispatchToProps(dispatch) {
    return {
        userActions: bindActionCreators(userActions, dispatch)
    }
}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Login))