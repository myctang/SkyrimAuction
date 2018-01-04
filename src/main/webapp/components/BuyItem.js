import React from 'react'
import { Card, CardMedia, CardTitle, CardText, CardActions } from 'react-toolbox/lib/card';
import {Button} from 'react-toolbox/lib/button';

class BuyItem extends React.Component{
    render() { return(
        <div className="buyItemCard">
            <Card style={{width: '300px'}}>
                <CardTitle
                    title={this.props.item.name}
                    subtitle={this.props.item.price}
                />
                <CardText>Вес:{this.props.item.item.weight} <br/> Атака: {this.props.item.item.damage} <br/> Защита: {this.props.item.item.defence}</CardText>
                <CardActions>
                    <Button label="Открыть" />
                </CardActions>
            </Card>
        </div>
    )
    }
}