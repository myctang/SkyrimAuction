import React from 'react'
import $ from 'jquery';

import AppBar from 'react-toolbox/lib/app_bar';
import Navigation from 'react-toolbox/lib/navigation';
import Button from 'react-toolbox/lib/Button';
import Chip from 'react-toolbox/lib/chip';
import Login from './Login'
import {MainRouting} from "./MainRouting";
import * as loginActions from '../actions/loginAction'
import * as userActions from '../actions/userActions'
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Link, withRouter} from 'react-router-dom'

export class MainPage extends React.Component {

    logout = () => {
        $.removeCookie("access_token");
        this.props.loginActions.logout();
    };

    constructor(props) {
        super(props);
        if (!!$.cookie('access_token')) {
            this.props.loginActions.loginSuccess($.cookie('access_token'));
            this.props.userActions.getUserInfo($.cookie('access_token'))
        }
    }

    render() {
        console.log("reder main page");
        debugger;
        let userField = [];
        let linkButton = [];
        if (this.props.login) {
            if (this.props.user !== undefined) {
                userField.push(<Chip>Ваш баланс: {this.props.user.money}</Chip>);
            }
            userField.push(
                <Link to="/me">
                    <Button label="Личный кабинет"/>
                </Link>
            );
            userField.push(<Button label="Выйти" onClick={this.logout}/>);
            linkButton.push(
                <Link to="/sell">
                    <Button label="Продать" raised primary/>
                </Link>
            );
            linkButton.push(
                <Link to="/mysells">
                    <Button label="Мои лоты" raised primary/>
                </Link>
            );
        }
        else {
            userField.push(
                <Login loginSuccess={this.props.loginActions}/>
            );
            linkButton.push(<Button label="Продать" disabled raised primary/>);
            linkButton.push(<Button label="Мои лоты" disabled raised primary/>);
        }

        return (
            <div>
                <AppBar title='Skyrim Auction'>
                    <Navigation type='horizontal' className="navigate">
                        <div className="buttonsMenu">
                            <div className="buttons">
                                <Link to="/">
                                    <Button label="Купить" raised primary/>
                                </Link>
                                {linkButton}
                                <Link to="/quests">
                                    <Button label="Задания" raised primary/>
                                </Link>
                            </div>
                        </div>
                        <div className="cabinet">
                            {userField}
                        </div>
                    </Navigation>
                </AppBar>
                <MainRouting/>
            </div>
        )
    }
}

function mapStateToProps(state) {
    console.log("mapStateToProps mainPage page");
    debugger;
    return {
        login: state.login.login,
        user: state.user.user,
    }
}

function mapDispatchToProps(dispatch) {
    return {
        loginActions: bindActionCreators(loginActions, dispatch),
        userActions: bindActionCreators(userActions, dispatch)
    }
}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(MainPage))