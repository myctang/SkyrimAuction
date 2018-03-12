import React from 'react'
import {Card, CardTitle, CardText, CardActions} from 'react-toolbox/lib/card';
import {Button} from 'react-toolbox/lib/button';
import Dialog from 'react-toolbox/lib/dialog';
import Input from 'react-toolbox/lib/input';
import {withRouter} from "react-router-dom";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import * as buyActions from '../actions/buyActions'
import $ from "jquery";

export class BuyItem extends React.Component {

    constructor(props) {
        super(props);
        this.makeBid = this.makeBid.bind(this);
    }

    state = {
        active: false,
        bid: ''
    };

    handleToggle = () => {
        this.setState({active: !this.state.active});
    };

    handleChange = (name, value) => {
        this.setState({...this.state, [name]: value});
    };

    makeBid = () => {
        console.log("making bid");
        debugger;
        if (this.props.buyNowPrice <= parseInt(this.state.bid)){
            $("#byuNowBidError").show();
            return;
        }
        this.props.buyActions.makeBid(this.props.id, this.state.bid, $.cookie("access_token"));
    };

    buyNow = () => {
        this.props.buyActions.buyNow(this.props.id, $.cookie("access_token"));
    };

    actions = [
        {label: "Поставить", onClick: this.makeBid},
        {label: "Купить сейчас", onClick: this.buyNow},
        {label: "Закрыть", onClick: this.handleToggle}
    ];

    render() {
        console.log("butItem->render");
        debugger;
        let currentDate = new Date();
        let min = Math.floor((this.props.sellingEnd - currentDate.getTime()) / 60000);
        let sec = Math.floor(((this.props.sellingEnd - currentDate.getTime()) / 1000)) - min * 60;
        if (min < 0 || sec < 0) {
            min = 0;
            sec = 0;
            // this.props.buyActions.getBuyList();
        }
        let style = {width: '300px'};
        if (this.props.lastBidder !== null && this.props.lastBidder.id === this.props.user.id){
            style = {width: '300px', background: 'lightgreen'};
        }
        let lastBidder
        if (this.props.lastBidder === undefined || this.props.lastBidder === null){
            lastBidder = <span>Начальная ставка - {this.props.price}</span>
        }else{
            lastBidder = <span>{this.props.lastBidder.name} - {this.props.price}</span>
        }
        console.log("render Buy with time " + min + " sec " + sec);
        return (
            <div className="buyItemCard">
                <Card style={style}>
                    <CardTitle
                        title={this.props.item.name}
                        subtitle={this.props.price}
                    />
                    <CardText>Вес:{this.props.item.weight} <br/> Атака: {this.props.item.damage}
                        <br/> Защита: {this.props.item.defence}</CardText>
                    <CardActions>
                        <Button label="Открыть" onClick={this.handleToggle}/>
                    </CardActions>
                </Card>
                <Dialog
                    actions={this.actions}
                    active={this.state.active}
                    onEscKeyDown={this.handleToggle}
                    onOverlayClick={this.handleToggle}
                    title={this.props.item.name}
                >
                    Вес:{this.props.item.weight} <br/>
                    Атака: {this.props.item.damage} <br/>
                    Защита: {this.props.item.defence}<br/>
                    Текущая ставка:  {lastBidder}<br/>
                    Купить сейчас: {this.props.buyNowPrice}<br/>
                    Закончится через: <span className="timer"><span className="minutes">{min}</span>:<span
                    className="seconds">{sec}</span></span>
                    <Input type='text' label='Ставка' name='bid' value={this.state.bid}
                           onChange={this.handleChange.bind(this, 'bid')} maxLength={16}/>
                    <p id="byuNowBidError" className="error">Нельзя сделать ставку больше или равную цене купить сейчас</p>
                </Dialog>
            </div>
        )
    }

    tick = () => {
        console.log("started");
        let sec = parseInt($(".timer .seconds").text());
        console.log(sec);
        if (sec === 0) {
            let min = parseInt($(".timer .minutes").text());
            if (min === 0) {
                this.setState({active:false});
                // this.props.buyActions.getBuyList();
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

    componentDidMount() {
        this.interval = setInterval(this.tick, 1000);
    }

    componentWillUnmount() {
        clearInterval(this.interval);
    }

}

function mapStateToProps(state) {
    console.log("mapStateToProps buyItem");
    return {
        user: state.user.user
    }
}

function mapDispatchToProps(dispatch) {
    return {
        buyActions: bindActionCreators(buyActions, dispatch)
    }
}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(BuyItem))