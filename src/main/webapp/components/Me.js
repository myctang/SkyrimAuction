import $ from 'jquery';
import React from 'react';
import {connect} from "react-redux";
import {withRouter} from "react-router-dom";
import {bindActionCreators} from "redux";
import {Card, CardTitle, CardText} from 'react-toolbox/lib/card';
import Dialog from 'react-toolbox/lib/dialog';
import {Button} from 'react-toolbox/lib/button';
import * as buyActions from "../actions/buyActions";
import * as userActions from "../actions/userActions";
import Input from 'react-toolbox/lib/input';

class Me extends React.Component{
    state = {
        dialogOpened: false,
        firstName: "",
        lastName: "",
        telegramID: ""
    }

    constructor(props){
        super(props);
        if (!!$.cookie('access_token')) {
            console.log("mySellItems->constructor");
            debugger;
            this.props.buyActions.getMyBidds(($.cookie("access_token")));
        }
        
    }

    tick = () => {
        console.log("started");
        let sec = parseInt($(".timer .seconds").text());
        console.log(sec);
        if (sec === 0) {
            let min = parseInt($(".timer .minutes").text());
            if (min === 0) {
                this.setState({active:false});
                this.props.mySellItemsActions.getMySellItems($.cookie("access_token"));
                return;
            }
            min--;
            $(".timer .minutes").text(min);
            $(".timer .seconds").text("59");
            return;
        }
        sec--;
        $(".timer .seconds").text(sec);
    };

    openCloseChangeDialog = () => {
        this.setState({...this.state,
            dialogOpened: !this.state.dialogOpened,
            firstName: this.props.user.firstName,
            secondName: this.props.user.secondName,
            telegramID: this.props.user.telegramID
        })
    }

    componentDidMount() {
        this.interval = setInterval(this.tick, 1000);
    }

    componentWillUnmount() {
        clearInterval(this.interval);
    }

    handleChange = (name, value) => {
        this.setState({...this.state, [name]: value});
    };

    changeBio = () => {
        let data = {
            "firstName": this.state.firstName,
            "secondName": this.state.lastName,
            "telegramID": this.state.telegramID
        }
        let token = $.cookie("access_token");

        $.ajax({
            url: "/api/user/changeBio",
            method: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization", "Bearer " + token);
            }
        }).then((data) => {
            this.props.userActions.setUserInfo(data)
            this.setState({...this.state, dialogOpened: false})
        })
    }

    actions = [
        {label: "Сохранить", onClick: this.changeBio},
        {label: "Отмена", onClick: this.openCloseChangeDialog}
    ]

    render(){
        let items = [];
        console.log("Me->render");
        debugger;
        this.props.items.forEach((item) => {
            let currentDate = new Date();
            let min = Math.floor((item.sellingEnd - currentDate.getTime()) / 60000);
            let sec = Math.floor(((item.sellingEnd - currentDate.getTime()) / 1000)) - min * 60;
            if ((min < 0 || sec < 0) && !item.finished) {
                min = 0;
                sec = 0;
                this.props.mySellItemsActions.getMySellItems($.cookie("access_token"));
            }
            let time = [];
            time.push(
                <div>
                    <br/> Закончится через: <span className="timer"><span className="minutes">{min}</span>:<span
                    className="seconds">{sec}</span></span>
                </div>
            )
            
            let style = {width: '300px', color: 'black'}
            items.push(
                <Card style={style}>
                    <CardTitle
                        title={item.item.name}
                        subtitle={item.item.price}
                    />
                    <CardText>Вес:{item.item.weight}
                        <br/> Атака: {item.item.damage}
                        <br/> Защита: {item.item.defence}
                        <br/> Последняя ставка: {item.lastBidder.name} - {item.price}
                        {time}
                    </CardText>
                </Card>
            )
        });

        return(
            <div className="content">
                Логин: {this.props.user.name}<br/>
                Имя: {this.props.user.firstName}<br/>
                Фамилия: {this.props.user.secondName}<br/>
                Telegram ID: {this.props.user.telegramID}<br/>
                <Dialog
                    actions={this.actions}
                    active={this.state.dialogOpened}
                    onEscKeyDown={this.openCloseChangeDialog}
                    onOverlayClick={this.openCloseChangeDialog}
                    title='My awesome dialog'
                >
                    <Input type="text" label="Имя" name="firstName" value={this.state.firstName} onChange={this.handleChange.bind(this, 'firstName')}/>
                    <Input type="text" label="Фамилия" name="lastName" value={this.state.lastName} onChange={this.handleChange.bind(this, 'lastName')}/>
                    <Input type="text" label="Telegram ID" name="telegramID" value={this.state.telegramID} onChange={this.handleChange.bind(this, 'telegramID')}/>
                </Dialog>

                <Button label="Изменить" onClick={this.openCloseChangeDialog} />

                {items}
            </div>
        )
    }
}

const mapStateToProps = (state) => {
    console.log("Me->mapStateToProps");
    debugger;
    return{
        user: state.user.user,
        items: state.getBuyList.myBidds
    }
}

function mapDispatchToProps(dispatch) {
    return {
        buyActions: bindActionCreators(buyActions, dispatch),
        userActions:bindActionCreators(userActions, dispatch)
    }
}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Me))
