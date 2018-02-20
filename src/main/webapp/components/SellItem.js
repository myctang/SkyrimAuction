import React from 'react'
import {Card, CardTitle, CardText, CardActions} from 'react-toolbox/lib/card';
import {Button} from 'react-toolbox/lib/button';
import Dialog from 'react-toolbox/lib/dialog';
import Input from 'react-toolbox/lib/input';
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Sell} from "./Sell";
import {withRouter} from "react-router-dom";
import * as sellActions from "../actions/sellActions";
import $ from "jquery";

export class SellItem extends React.Component {
    state = {
        active: false,
        StartBid: '',
        duration: '',
        buyNow: '',
        id: 0
    };

    constructor(props){
        super(props);
        this.CreateAuction = this.CreateAuction.bind(this);
    }

    handleToggle = () => {
        debugger;
        this.setState({active: !this.state.active});
    };

    handleChange = (name, value) => {
        this.setState({...this.state, [name]: value});
    };

    CreateAuction = () => {
        console.log("creating");
        $(".error").hide();
        if ((isNaN(parseInt(this.state.buyNow)) || isNaN(parseInt(this.state.StartBid)) || isNaN(parseInt(this.state.duration))) || (parseInt(this.state.buyNow)) <= parseInt(this.state.StartBid)){
            $("#error_input").show();
            return;
        }
        debugger;
        const { createAuction } = this.props.sellActions;
        createAuction(this.props.item.id, this.state.StartBid, this.state.buyNow, this.state.duration, $.cookie("access_token"));
        this.handleToggle();
    };

    actions = [
        {label: "Закрыть", onClick: this.handleToggle}
    ];

    render() {
        console.log("sellItem render");
        debugger;
        return (
            <div className="buyItemCard">
                <Card style={{width: '300px'}}>
                    <CardTitle
                        title={this.props.item.name}
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
                    Вес: {this.props.item.weight} <br/>
                    Атака: {this.props.item.damage} <br/>
                    Защита: {this.props.item.defence}<br/>
                    <Input type='text' label='Начальная ставка' name='StartBid' value={this.state.StartBid}
                           onChange={this.handleChange.bind(this, 'StartBid')}/>
                    <Input type='text' label='Купить сейчас' name='buyNow' value={this.state.buyNow}
                           onChange={this.handleChange.bind(this, 'buyNow')}/>
                    <Input type='text' label='Длительность аукциона (в мин.)' name='duration'
                           value={this.state.duration} onChange={this.handleChange.bind(this, 'duration')}/>
                    <p id="error_input" className=".error">Введите начальную ставку и стоимость купить сейчас, а так же длительность аукциона. Начальная ставка должна быть меньше стоимости купить сейчас.</p>
                    <Button label="Выставить" onClick={this.CreateAuction}/>
                </Dialog>
            </div>
        )
    }
}

function mapStateToProps(state) {
    console.log("mapStateToProps mainPage");
    return {}
}

function mapDispatchToProps(dispatch){
    return{
        sellActions: bindActionCreators(sellActions, dispatch)
    }
}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(SellItem))